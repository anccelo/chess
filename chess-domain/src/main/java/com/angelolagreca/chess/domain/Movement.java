package com.angelolagreca.chess.domain;

import com.angelolagreca.chess.domain.vo.Color;
import com.angelolagreca.chess.domain.piece.TypeOfPiece;

public class Movement {

    private final TypeOfPiece typeOfPiece;
    private final Game actualGame;

    public Movement(TypeOfPiece typeOfPiece, Game actualGame) {
        this.typeOfPiece = typeOfPiece;
        this.actualGame = actualGame;
    }

    public boolean isAllowed(Chessboard oldPoistion, Chessboard newPosition) {
        switch (this.typeOfPiece) {
            case WHITE_KING:
            case BLACK_KING:
                return kingMovement(oldPoistion, newPosition);
            case WHITE_QUEEN:
            case BLACK_QUEEN:
                return true;
            case WHITE_BISHOP:
            case BLACK_BISHOP:
                return bishopMovement(oldPoistion, newPosition);
            case WHITE_KNIGHT:
            case BLACK_KNIGHT:
                return knightMovement(oldPoistion, newPosition);
            case WHITE_ROOK:
            case BLACK_ROOK:
                return rookMovement(oldPoistion, newPosition);
            case WHITE_PAWN:
                return whitePawnMovement(oldPoistion, newPosition);
            case BLACK_PAWN:
                return blackPawnMovement(oldPoistion, newPosition);
            default:
                return false;
        }
    }

    public boolean isTargetPositionOccupiedByAPieceOfItsOwnColour(Chessboard actualPosition,
                                                                  Chessboard targetPosition) {
        Color colorOfPieceInGame = actualGame.getChessboardPieceMap().get(actualPosition).getColor();
        Color colorOfPieceInTargetPosition = actualGame.getChessboardPieceMap().get(targetPosition).getColor();

        return colorOfPieceInGame.equals(colorOfPieceInTargetPosition);

    }

    private boolean kingMovement(Chessboard actualPosition, Chessboard targetPosition) {
        if (itHasntMoved(actualPosition, targetPosition)) return false;

        int checkX = Math.abs(actualPosition.getPosition().getX() - targetPosition.getPosition().getX());
        int checkY = Math.abs(actualPosition.getPosition().getY() - targetPosition.getPosition().getY());

        if (checkX > 1 || checkY > 1)
            return checkX != 0 && checkY != 0;
        return true;
    }

    private boolean whitePawnMovement(Chessboard actualPosition, Chessboard newPosition) {
        if (itHasntMoved(actualPosition, newPosition))
            return false;
        if (isAWhitePawnStartPosition(actualPosition)) {
            return startWhitePawnMovement(actualPosition, newPosition);
        } else {
            return singleWhitePawnMovement(actualPosition, newPosition);
        }
    }

    private static boolean isAWhitePawnStartPosition(Chessboard actualPosition) {
        return actualPosition.getPosition().getY() == 2;
    }

    private boolean blackPawnMovement(Chessboard actualPosition, Chessboard newPosition) {
        if (itHasntMoved(actualPosition, newPosition))
            return false;
        if (isABlackPawnStartPosition(actualPosition)) {
            return startBlackPawnMovement(actualPosition, newPosition);
        }
        return singleBlackPawnMovement(actualPosition, newPosition);
    }

    private boolean singleWhitePawnMovement(Chessboard actualPosition, Chessboard newPosition) {

        int checkX = Math.abs(actualPosition.getPosition().getX() - newPosition.getPosition().getX());
        int checkY = newPosition.getPosition().getY() - actualPosition.getPosition().getY();
        return (checkX == 0 && checkY == 1);
    }

    private boolean startWhitePawnMovement(Chessboard actualPosition, Chessboard newPosition) {

        int checkX = Math.abs(actualPosition.getPosition().getX() - newPosition.getPosition().getX());
        int checkY = Math.abs(actualPosition.getPosition().getY() - newPosition.getPosition().getY());

        return (singleWhitePawnMovement(actualPosition, newPosition) || (checkX == 0 && checkY == 2));
    }

    private boolean singleBlackPawnMovement(Chessboard actualPosition, Chessboard newPosition) {

        int checkX = Math.abs(actualPosition.getPosition().getX() - newPosition.getPosition().getX());
        int checkY = actualPosition.getPosition().getY() - newPosition.getPosition().getY();
        return (checkX == 0 && checkY == 1);

    }

    private boolean startBlackPawnMovement(Chessboard actualPosition, Chessboard newPosition) {

        if (actualPosition.getPosition().getY() == 7) {
            int checkX = Math.abs(actualPosition.getPosition().getX() - newPosition.getPosition().getX());
            int checkY = Math.abs(newPosition.getPosition().getY() - actualPosition.getPosition().getY());

            return (singleBlackPawnMovement(actualPosition, newPosition) || (checkX == 0 && checkY == 2));
        }
        return false;
    }

    private static boolean isABlackPawnStartPosition(Chessboard actualPosition) {
        return actualPosition.getPosition().getY() == 7;
    }

    private boolean bishopMovement(Chessboard actualPosition, Chessboard newPosition) {
        if (itHasntMoved(actualPosition, newPosition)) return false;

        int checkX = Math.abs(actualPosition.getPosition().getX() - newPosition.getPosition().getX());
        int checkY = Math.abs(actualPosition.getPosition().getY() - newPosition.getPosition().getY());
        return checkX == checkY;
    }


    private boolean rookMovement(Chessboard actualPosition, Chessboard newPosition) {
        if (itHasntMoved(actualPosition, newPosition)) return false;
        int checkX = Math.abs(actualPosition.getPosition().getX() - newPosition.getPosition().getX());
        int checkY = Math.abs(actualPosition.getPosition().getY() - newPosition.getPosition().getY());

        return (checkX != 0 && checkY == 0 || checkY != 0 && checkX == 0);

    }

    private boolean knightMovement(Chessboard actualPosition, Chessboard newPosition) {
        if (itHasntMoved(actualPosition, newPosition))
            return false;
        int checkX = Math.abs(actualPosition.getPosition().getX() - newPosition.getPosition().getX());
        int checkY = Math.abs(actualPosition.getPosition().getY() - newPosition.getPosition().getY());

        return (checkX == 1 && checkY == 2 || checkY == 1 && checkX == 2);

    }

    private boolean itHasntMoved(Chessboard actualPosition, Chessboard newPosition) {
        return newPosition.equals(actualPosition);
    }
}

