package com.angelolagreca.chess.domain.piece;

import com.angelolagreca.chess.domain.Position;
import com.angelolagreca.chess.domain.exception.IllegalPositionException;
import lombok.Getter;
import lombok.ToString;

@Getter
public class EmptyPiece extends Piece {

    @Override
    public void moveIn(Position newPositon) {
    }

    @Override
    public boolean isAllowedMoveIn(Position newPosition) throws IllegalPositionException {
        return false;
    }

    @Override
    public String toString() {
        return "EMPTY";
    }
}
