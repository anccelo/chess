package com.angelolagreca.chess.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.angelolagreca.chess.domain.piece.Color.BLACK;
import static com.angelolagreca.chess.domain.piece.Color.WHITE;

@Getter
@AllArgsConstructor
public class Game {

    private Player whitePlayer;
    private Player blackPlayer;

    Chessboard chessboard = new Chessboard();

    public Game() {
        this.whitePlayer = new Player(WHITE);
        this.blackPlayer = new Player(BLACK);
    }
}
