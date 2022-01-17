package com.angelolagreca.chess.domain;

public class Movement {

    public static boolean moved(Position actualPosition, Position newPosition){
        return !newPosition.equals(actualPosition);
    }

    public static boolean kingMovemet(Position actualPosition, Position newPosition) {
        if(!moved(actualPosition,newPosition)) return false;

        int checkX = Math.abs(actualPosition.getX() - newPosition.getX());
        int checkY = Math.abs(actualPosition.getY() - newPosition.getY());

        if (checkX > 1 || checkY > 1)
            if (checkX == 0 || checkY == 0)
                return false;
        return true;
    }

    public static boolean WhitePawnMovement(Position actualPosition, Position newPosition) {
        if(!moved(actualPosition,newPosition)) return false;

        int checkX = Math.abs(actualPosition.getX() - newPosition.getX());
        int checkY = newPosition.getY() - actualPosition.getY();
        return (checkX == 0 && checkY == 1);
    }

    public static boolean initialWhitePawnMovement(Position actualPosition, Position newPosition) {
        if(!moved(actualPosition,newPosition)) return false;

        int checkX = Math.abs(actualPosition.getX() - newPosition.getX());
        int checkY = Math.abs(actualPosition.getY() - newPosition.getY());

        return (WhitePawnMovement(actualPosition, newPosition) || (checkX == 0 && checkY == 2));
    }

}
