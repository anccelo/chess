package com.angelolagreca.chess.domain;

public class Movement {

    protected static boolean didItMovedFrom(Position actualPosition, Position newPosition) {
        return !newPosition.equals(actualPosition);
    }

    public static boolean kingMovemet(Position actualPosition, Position newPosition) {
        if (!didItMovedFrom(actualPosition, newPosition)) return false;

        int checkX = Math.abs(actualPosition.getX() - newPosition.getX());
        int checkY = Math.abs(actualPosition.getY() - newPosition.getY());

        if (checkX > 1 || checkY > 1)
            if (checkX == 0 || checkY == 0)
                return false;
        return true;
    }

    public static boolean WhitePawnMovement(Position actualPosition, Position newPosition) {
        if (!didItMovedFrom(actualPosition, newPosition)) return false;

        int checkX = Math.abs(actualPosition.getX() - newPosition.getX());
        int checkY = newPosition.getY() - actualPosition.getY();
        return (checkX == 0 && checkY == 1);
    }

    public static boolean initialWhitePawnMovement(Position actualPosition, Position newPosition) {
        if (!didItMovedFrom(actualPosition, newPosition)) return false;

        int checkX = Math.abs(actualPosition.getX() - newPosition.getX());
        int checkY = Math.abs(actualPosition.getY() - newPosition.getY());

        return (WhitePawnMovement(actualPosition, newPosition) || (checkX == 0 && checkY == 2));
    }

    public static boolean BlackPawnMovement(Position actualPosition, Position newPosition) {
        if (!didItMovedFrom(actualPosition, newPosition)) return false;

        int checkX = Math.abs(actualPosition.getX() - newPosition.getX());
        int checkY = actualPosition.getY() - newPosition.getY();
        return (checkX == 0 && checkY == 1);

    }

    public static boolean initialBlackPawnMovement(Position actualPosition, Position newPosition) {
        if (!didItMovedFrom(actualPosition, newPosition)) return false;

        if (actualPosition.getY() == 7) {
            int checkX = Math.abs(actualPosition.getX() - newPosition.getX());
            int checkY = Math.abs(newPosition.getY() - actualPosition.getY());

            return (WhitePawnMovement(actualPosition, newPosition) || (checkX == 0 && checkY == 2));
        }
        return false;
    }

    public static boolean bishopMovemet(Position actualPosition, Position newPosition) {
        if (!didItMovedFrom(actualPosition, newPosition)) return false;

        int checkX = Math.abs(actualPosition.getX() - newPosition.getX());
        int checkY = Math.abs(actualPosition.getY() - newPosition.getY());
        return checkX == checkY;
    }


    public static boolean rookMovemet(Position actualPosition, Position newPosition) {
        if (!didItMovedFrom(actualPosition, newPosition)) return false;
        int checkX = Math.abs(actualPosition.getX() - newPosition.getX());
        int checkY = Math.abs(actualPosition.getY() - newPosition.getY());

        return (checkX != 0 && checkY == 0 || checkY != 0 && checkX == 0 );

    }

    public static boolean knightMovemet(Position actualPosition, Position newPosition) {
        if (!didItMovedFrom(actualPosition, newPosition)) return false;

        int checkX = Math.abs(actualPosition.getX() - newPosition.getX());
        int checkY = Math.abs(actualPosition.getY() - newPosition.getY());

        return (checkX == 1 && checkY == 2 || checkY == 1 && checkX == 2 );

    }
}
