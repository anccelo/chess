package com.angelolagreca.chess.domain.piece;

import com.angelolagreca.chess.domain.Movement;
import com.angelolagreca.chess.domain.Position;
import com.angelolagreca.chess.domain.exception.IllegalPositionException;
import com.angelolagreca.chess.domain.exception.InitializationException;
import lombok.Getter;

import static com.angelolagreca.chess.domain.piece.Color.BLACK;
import static com.angelolagreca.chess.domain.piece.Color.WHITE;

@Getter
public class King extends Piece {

    public King(Color color) throws InitializationException {
        if (color == WHITE)
            this.position = new Position('E', 1);
        else if (color == BLACK)
            position = new Position('E', 1);
        this.color = color;

    }

    @Override
    public Position moveIn(Position newPosition) {

        return null;
    }

    @Override
    public boolean isMovementAllowed(Position newPosition)  {
        return Movement.kingMovemet(this.position, newPosition);
    }

}
