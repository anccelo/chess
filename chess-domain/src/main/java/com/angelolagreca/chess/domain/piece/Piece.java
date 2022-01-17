package com.angelolagreca.chess.domain.piece;

import com.angelolagreca.chess.domain.Position;
import com.angelolagreca.chess.domain.exception.IllegalPositionException;
import lombok.Getter;

@Getter
public abstract class Piece {

    protected Color color;
    protected Position position;

    public abstract Position moveIn(Position newPositon);
    public abstract boolean isMovementAllowed(Position newPosition) throws IllegalPositionException;

    //is not the class for this methode, is more for chessboard


    protected  void isPlaceFreeFromMyChess(Position newPosition){

    }
}
