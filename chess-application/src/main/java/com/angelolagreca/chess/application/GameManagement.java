package com.angelolagreca.chess.application;

import com.angelolagreca.chess.domain.Chessboard;
import com.angelolagreca.chess.domain.Game;
import com.angelolagreca.chess.domain.exception.PieceMovementException;

public interface GameManagement {

    Game init();

    Game playerMove(Game game, Chessboard oldPosition, Chessboard newPosition) throws PieceMovementException;

}
