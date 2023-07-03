package com.angelolagreca.chess.domain;

import com.angelolagreca.chess.domain.piece.TypeOfPiece;

public class Movement {

    private final TypeOfPiece typeOfPiece;

    public Movement(TypeOfPiece typeOfPiece) {
        this.typeOfPiece = typeOfPiece;
    }


    public boolean isAllowed(ChessboardPosition oldPoistion, ChessboardPosition newPosition) {
        switch (this.typeOfPiece) {
            case WHITE_KING:
            case BLACK_KING:
                return kingMovemet(oldPoistion, newPosition);
            case WHITE_QUEEN:
            case BLACK_QUEEN:
                return true;
            case WHITE_BISHOP:
            case BLACK_BISHOP:
                return bishopMovemet(oldPoistion, newPosition);
            case WHITE_KNIGHT:
            case BLACK_KNIGHT:
                return knightMovemet(oldPoistion, newPosition);
            case WHITE_ROOK:
            case BLACK_ROOK:
                return rookMovemet(oldPoistion, newPosition);
            case WHITE_PAWN:
                return whitePawnMovement(oldPoistion, newPosition);
            case BLACK_PAWN:
                return blackPawnMovement(oldPoistion, newPosition);
            default:
                return false;
        }
    }

    private boolean kingMovemet(ChessboardPosition actualPosition, ChessboardPosition newPosition) {
        if (itHasntMoved(actualPosition, newPosition)) return false;

        int checkX = Math.abs(actualPosition.getX() - newPosition.getX());
        int checkY = Math.abs(actualPosition.getY() - newPosition.getY());

        if (checkX > 1 || checkY > 1)
            return checkX != 0 && checkY != 0;
        return true;
    }

    private boolean whitePawnMovement(ChessboardPosition actualPosition, ChessboardPosition newPosition) {
        if (itHasntMoved(actualPosition, newPosition)) return false;

        int checkX = Math.abs(actualPosition.getX() - newPosition.getX());
        int checkY = newPosition.getY() - actualPosition.getY();
        return (checkX == 0 && checkY == 1);
    }

    private boolean initialWhitePawnMovement(ChessboardPosition actualPosition, ChessboardPosition newPosition) {
        if (itHasntMoved(actualPosition, newPosition)) return false;

        int checkX = Math.abs(actualPosition.getX() - newPosition.getX());
        int checkY = Math.abs(actualPosition.getY() - newPosition.getY());

        return (whitePawnMovement(actualPosition, newPosition) || (checkX == 0 && checkY == 2));
    }

    private boolean blackPawnMovement(ChessboardPosition actualPosition, ChessboardPosition newPosition) {
        if (itHasntMoved(actualPosition, newPosition)) return false;

        int checkX = Math.abs(actualPosition.getX() - newPosition.getX());
        int checkY = actualPosition.getY() - newPosition.getY();
        return (checkX == 0 && checkY == 1);

    }

    private boolean initialBlackPawnMovement(ChessboardPosition actualPosition, ChessboardPosition newPosition) {
        if (itHasntMoved(actualPosition, newPosition))
            return false;

        if (actualPosition.getY() == 7) {
            int checkX = Math.abs(actualPosition.getX() - newPosition.getX());
            int checkY = Math.abs(newPosition.getY() - actualPosition.getY());

            return (whitePawnMovement(actualPosition, newPosition) || (checkX == 0 && checkY == 2));
        }
        return false;
    }

    private boolean bishopMovemet(ChessboardPosition actualPosition, ChessboardPosition newPosition) {
        if (itHasntMoved(actualPosition, newPosition)) return false;

        int checkX = Math.abs(actualPosition.getX() - newPosition.getX());
        int checkY = Math.abs(actualPosition.getY() - newPosition.getY());
        return checkX == checkY;
    }


    private boolean rookMovemet(ChessboardPosition actualPosition, ChessboardPosition newPosition) {
        if (itHasntMoved(actualPosition, newPosition)) return false;
        int checkX = Math.abs(actualPosition.getX() - newPosition.getX());
        int checkY = Math.abs(actualPosition.getY() - newPosition.getY());

        return (checkX != 0 && checkY == 0 || checkY != 0 && checkX == 0);

    }

    private boolean knightMovemet(ChessboardPosition actualPosition, ChessboardPosition newPosition) {
        if (itHasntMoved(actualPosition, newPosition)) return false;

        int checkX = Math.abs(actualPosition.getX() - newPosition.getX());
        int checkY = Math.abs(actualPosition.getY() - newPosition.getY());

        return (checkX == 1 && checkY == 2 || checkY == 1 && checkX == 2);

    }

    private boolean itHasntMoved(ChessboardPosition actualPosition, ChessboardPosition newPosition) {
        return newPosition.equals(actualPosition);
    }
}

