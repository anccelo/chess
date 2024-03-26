package com.angelolagreca.chess.domain;

import com.angelolagreca.chess.domain.piece.*;
import com.angelolagreca.chess.domain.vo.*;

public class Movement {

    private final TypeOfPiece typeOfPiece;
    private final Game actualGame;

    public Movement(TypeOfPiece typeOfPiece, Game actualGame) {
        this.typeOfPiece = typeOfPiece;
        this.actualGame = actualGame;
    }

    public boolean isAllowed(Game game, Chessboard actualPosition, Chessboard targetPosition) {
        switch (this.typeOfPiece) {
            case WHITE_KING:
            case BLACK_KING:
                return kingMovement(actualPosition, targetPosition);
            case WHITE_QUEEN:
            case BLACK_QUEEN:
                return queenMovement(actualPosition, targetPosition);
            case WHITE_BISHOP:
            case BLACK_BISHOP:
                return bishopMovement(actualPosition, targetPosition);
            case WHITE_KNIGHT:
            case BLACK_KNIGHT:
                return knightMovement(actualPosition, targetPosition);
            case WHITE_ROOK:
            case BLACK_ROOK:
                return rookMovement(actualPosition, targetPosition);
            case WHITE_PAWN:
                return whitePawnMovement(game, actualPosition, targetPosition);
            case BLACK_PAWN:
                return blackPawnMovement(game, actualPosition, targetPosition);
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

        int checkX = getHowManyHorizontalPositionsThePieceWantsToMove(actualPosition, targetPosition);
        int checkY = Math.abs(actualPosition.getPosition().getY() - targetPosition.getPosition().getY());

        return checkX <= 1 && checkY <= 1 && !itHasntMoved(actualPosition, targetPosition);
    }

    //pawnMovememnt


    private boolean whitePawnMovement(Game game, Chessboard actualPosition, Chessboard targetPosition) {
        if (itHasntMoved(actualPosition, targetPosition))
            return false;
        if (isAWhitePawnStartPosition(actualPosition)) {
            return startWhitePawnMovement(game, actualPosition, targetPosition);
        } else {
            return singleWhitePawnMovement(game, actualPosition, targetPosition);
        }
    }

    private boolean blackPawnMovement(Game game, Chessboard actualPosition, Chessboard targetPosition) {
        if (itHasntMoved(actualPosition, targetPosition))
            return false;
        if (TypeOfPiece.EMPTY.equals(game.getChessboardPieceMap().get(targetPosition))
                && (isABlackPawnStartPosition(actualPosition))) {
            return startBlackPawnMovement(game, actualPosition, targetPosition);

        }
        return singleBlackPawnMovement(game, actualPosition, targetPosition);
    }

    private boolean singleWhitePawnMovement(Game game, Chessboard actualPosition, Chessboard targetPosition) {

        int checkX = getHowManyHorizontalPositionsThePieceWantsToMove(actualPosition, targetPosition);
        int checkY = targetPosition.getPosition().getY() - actualPosition.getPosition().getY();
        if (checkY == 1) {
            if (checkX == 1) {
                return Color.BLACK.equals(game.getChessboardPieceMap().get(targetPosition).getColor());
            } else {
                if (!TypeOfPiece.EMPTY.equals(game.getChessboardPieceMap().get(targetPosition))) {
                    return false;
                }
            }
            return checkX == 0;
        }
        return false;
    }

    private boolean singleBlackPawnMovement(Game game, Chessboard actualPosition, Chessboard targetPosition) {

        int checkX = getHowManyHorizontalPositionsThePieceWantsToMove(actualPosition, targetPosition);
        int checkY = actualPosition.getPosition().getY() - targetPosition.getPosition().getY();
        if (checkY == 1) {
            if (checkX == 1) {
                return Color.WHITE.equals(game.getChessboardPieceMap().get(targetPosition).getColor());
            } else {
                if (!TypeOfPiece.EMPTY.equals(game.getChessboardPieceMap().get(targetPosition))) {
                    return false;
                }
            }
            return checkX == 0;
        }
        return false;

    }

    private boolean startWhitePawnMovement(Game game, Chessboard actualPosition, Chessboard targetPosition) {

        int checkX = getHowManyHorizontalPositionsThePieceWantsToMove(actualPosition, targetPosition);
        int checkY = Math.abs(actualPosition.getPosition().getY() - targetPosition.getPosition().getY());

        return (singleWhitePawnMovement(game, actualPosition, targetPosition) || (checkX == 0 && checkY == 2));
    }

    private boolean startBlackPawnMovement(Game game, Chessboard actualPosition, Chessboard newPosition) {

        if (actualPosition.getPosition().getY() == 7) {
            int checkX = getHowManyHorizontalPositionsThePieceWantsToMove(actualPosition, newPosition);
            int checkY = actualPosition.getPosition().getY() - newPosition.getPosition().getY();

            return (singleBlackPawnMovement(game, actualPosition, newPosition) || (checkX == 0 && checkY == 2));
        }
        return false;
    }

    private static boolean isAWhitePawnStartPosition(Chessboard actualPosition) {
        return actualPosition.getPosition().getY() == 2;
    }

    private static boolean isABlackPawnStartPosition(Chessboard actualPosition) {
        return actualPosition.getPosition().getY() == 7;
    }

    //end pawn

    private boolean bishopMovement(Chessboard actualPosition, Chessboard newPosition) {
        if (itHasntMoved(actualPosition, newPosition)) return false;

        int checkX = getHowManyHorizontalPositionsThePieceWantsToMove(actualPosition, newPosition);
        int checkY = Math.abs(actualPosition.getPosition().getY() - newPosition.getPosition().getY());
        return checkX == checkY;
    }


    private boolean rookMovement(Chessboard actualPosition, Chessboard newPosition) {
        if (itHasntMoved(actualPosition, newPosition)) return false;
        int checkX = getHowManyHorizontalPositionsThePieceWantsToMove(actualPosition, newPosition);
        int checkY = Math.abs(actualPosition.getPosition().getY() - newPosition.getPosition().getY());

        return (checkX != 0 && checkY == 0 || checkY != 0 && checkX == 0);

    }

    private boolean knightMovement(Chessboard actualPosition, Chessboard newPosition) {
        if (itHasntMoved(actualPosition, newPosition))
            return false;
        int checkX = getHowManyHorizontalPositionsThePieceWantsToMove(actualPosition, newPosition);
        int checkY = Math.abs(actualPosition.getPosition().getY() - newPosition.getPosition().getY());

        return (checkX == 1 && checkY == 2 || checkY == 1 && checkX == 2);

    }

    private boolean queenMovement(Chessboard actualPosition, Chessboard newPosition) {
        return kingMovement(actualPosition, newPosition)
                || rookMovement(actualPosition, newPosition)
                || bishopMovement(actualPosition, newPosition);
    }


    private static int getHowManyHorizontalPositionsThePieceWantsToMove(Chessboard actualPosition, Chessboard targetPosition) {
        return Math.abs(actualPosition.getPosition().getX() - targetPosition.getPosition().getX());

    }

    private boolean itHasntMoved(Chessboard actualPosition, Chessboard newPosition) {
        return newPosition.equals(actualPosition);
    }
}

