package com.angelolagreca.api;


import com.angelolagreca.chess.application.GameManagement;
import com.angelolagreca.chess.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @Autowired
    private GameManagement gameManagement;

    @GetMapping(value = "/init")
    public Game init() {
        return gameManagement.init();
    }

}
