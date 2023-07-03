package com.angelolagreca.api;


import com.angelolagreca.chess.application.GameManagement;
import com.angelolagreca.chess.domain.ChessboardPosition;
import com.angelolagreca.chess.domain.Game;
import com.angelolagreca.chess.domain.piece.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    @Autowired
    private GameManagement gameManagement;

    @GetMapping(value = "init", produces = "application/json")
    public Game init() throws Exception {
        return gameManagement.init();
    }

    @PostMapping("/move/{color}/{oldPosition}/{newPosition}")
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

}
