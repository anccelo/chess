package com.angelolagreca.api;


import com.angelolagreca.chess.application.*;
import com.angelolagreca.chess.domain.*;
import com.angelolagreca.chess.domain.piece.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("game")
public class GameController {

    @Autowired
    private GameManagement gameManagement;


    @PostMapping(
            value = "/move/{color}/{oldPosition}/{newPosition}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Game> move(
            @RequestBody Game game,
            @PathVariable Color color,
            @PathVariable ChessboardPosition oldPosition,
            @PathVariable ChessboardPosition newPosition) throws Exception {
//        ChessboardPosition oldPoistionEnum = ChessboardPosition.valueOf(oldPosition);
//        ChessboardPosition newPositionEnum = ChessboardPosition.valueOf(newPosition);
        Game gameEvolution = gameManagement.playerMove(game, oldPosition, newPosition);
        return new ResponseEntity<>(gameEvolution, HttpStatus.OK);
    }

    @GetMapping(value = "init", produces = "application/json")
    public Game init() throws Exception {
        return gameManagement.init();
    }

}
