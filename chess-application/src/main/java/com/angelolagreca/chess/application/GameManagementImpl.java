package com.angelolagreca.chess.application;

import com.angelolagreca.chess.domain.*;
import com.angelolagreca.chess.domain.piece.*;

import java.util.*;

import static com.angelolagreca.chess.domain.piece.Color.BLACK;
import static com.angelolagreca.chess.domain.piece.Color.WHITE;

public class GameManagementImpl implements GameManagement{
    @Override
    public Game init() {

        Game game = new Game();

        Map<Position, Piece> chessboardOfGame = game.getChessboard().getCheesboard();
        Piece whiteKing = new King(WHITE);
        Piece blackKing = new King(BLACK);
        chessboardOfGame.put(whiteKing.getPosition(), whiteKing);
        chessboardOfGame.put(blackKing.getPosition(), blackKing);

        return game;
    }
}
