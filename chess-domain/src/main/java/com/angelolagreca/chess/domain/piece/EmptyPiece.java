package com.angelolagreca.chess.domain.piece;

import com.angelolagreca.chess.domain.Position;
import com.angelolagreca.chess.domain.exception.IllegalPositionException;

public class EmptyPiece extends Piece {
    @Override
    public Position moveIn(Position newPositon) {
        return null;
    }

    @Override
    public boolean isMovementAllowed(Position newPosition) throws IllegalPositionException {
        return false;
    }
}
