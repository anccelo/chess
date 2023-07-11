package com.angelolagreca.chess.application;

import com.angelolagreca.chess.domain.Chessboard;
import com.angelolagreca.chess.domain.Game;
import com.angelolagreca.chess.domain.Movement;
import com.angelolagreca.chess.domain.exception.PieceMovementException;
import com.angelolagreca.chess.domain.piece.TypeOfPiece;
import com.angelolagreca.chess.domain.vo.Position;
import org.springframework.stereotype.Component;

import static com.angelolagreca.chess.domain.Chessboard.D2;
import static com.angelolagreca.chess.domain.piece.TypeOfPiece.*;

@Component
public class GameManagementImpl implements GameManagement {

    @Override
    public Game init() {

        Game game = new Game();

        for (Chessboard chessboard : Chessboard.values()) {
            game.getChessboardPieceMap().put(chessboard, EMPTY);
        }
        initWhiteTeam(game);
        initBlackTeam(game);

        return game;
    }

    @Override
    public Game playerMove(Game game, Chessboard actualPosition, Chessboard newPosition) throws PieceMovementException {

        TypeOfPiece pieceToMove = determineThePieceToMove(game, actualPosition);
        moveThePiece(game, actualPosition, newPosition, pieceToMove);

        saveMoveIntoHistoricalOfThisGame(game);

        return game;
    }

    private static void saveMoveIntoHistoricalOfThisGame(Game game) {
        game.getHistoryChessboardPieceMap().put(game.incrementMoveCounter(), game.getChessboardPieceMap());
    }

    private static TypeOfPiece determineThePieceToMove(Game game, Chessboard actualPosition) {
        return game.getChessboardPieceMap().get(actualPosition);

    }

    private static void moveThePiece(Game game, Chessboard actualPosition, Chessboard targetPosition,
                                     TypeOfPiece pieceCheSiEdecisoDiMuovere) throws PieceMovementException {
        checkIfMovementIsPossible(pieceCheSiEdecisoDiMuovere, game, actualPosition, targetPosition);
        game.getChessboardPieceMap().put(actualPosition, EMPTY);
        game.getChessboardPieceMap().put(targetPosition, pieceCheSiEdecisoDiMuovere);
    }

    private static void checkIfMovementIsPossible(TypeOfPiece pieceToMove, Game game, Chessboard actualPosition,
                                                  Chessboard targetPosition) throws PieceMovementException {
        Movement movement = new Movement(pieceToMove, game);

        if (movement.isTargetPositionOccupiedByAPieceOfItsOwnColour(actualPosition, targetPosition)
                || !movement.isAllowed(actualPosition, targetPosition)
                || !theWayOnTheChessboardIsFree(game, actualPosition, targetPosition)) {

            throw new PieceMovementException("Movement is not possible.");
        }
    }

    private static boolean theWayOnTheChessboardIsFree(Game game, Chessboard actualPosition, Chessboard targetPosition) {

        if (!pieceIsAKnight(game, actualPosition)) {

            if (actualPosition.getPosition().getX() == targetPosition.getPosition().getX()) {
                return checkIfVerticalIsFree(game, actualPosition, targetPosition);
            }
            if (actualPosition.getPosition().getY() == targetPosition.getPosition().getY()) {
                return checkIfHorizontalIsFree(game, actualPosition, targetPosition);
            }
            //checkDiagonal
            return checkIfDiagonalIsFree(game, actualPosition, targetPosition);

        }
        return true;
    }

    private static boolean checkIfDiagonalIsFree(Game game, Chessboard actualPosition, Chessboard targetPosition) {
        int initialOrdinate = actualPosition.getPosition().getX();
        int finalOrdinate = targetPosition.getPosition().getX();
        int biggerOrdinate = Integer.max(initialOrdinate, finalOrdinate);
        int smallerOrdinate = Integer.min(initialOrdinate, finalOrdinate);

        int initialAbscissa = actualPosition.getPosition().getY();
        int finalAbscissa = targetPosition.getPosition().getY();
        int biggerAbscissa = Integer.max(initialAbscissa, finalAbscissa);
        int smallerAbscissa = Integer.min(initialAbscissa, finalAbscissa);

        for (int ordinate = smallerOrdinate + 1; ordinate < biggerOrdinate; ordinate++) {
//todo
        }
        return false;

    }

    private static boolean checkIfVerticalIsFree(Game game, Chessboard actualPosition, Chessboard targetPosition) {
        int initialOrdinate = actualPosition.getPosition().getY();
        int finalOrdinate = targetPosition.getPosition().getY();
        int bigger = Integer.max(initialOrdinate, finalOrdinate);
        int smaller = Integer.min(initialOrdinate, finalOrdinate);

        for (int ordinate = smaller + 1; ordinate < bigger; ordinate++) {
            Position position = new Position(actualPosition.getPosition().getX(), ordinate);
            TypeOfPiece typeOfPieceInCrossedPosition =
                    game.getChessboardPieceMap().get(Chessboard.valueOfPosition(position));
            if (!EMPTY.equals(typeOfPieceInCrossedPosition))
                return false;
        }
        return true;
    }

    private static boolean checkIfHorizontalIsFree(Game game, Chessboard actualPosition, Chessboard targetPosition) {
        int initialAbscissa = actualPosition.getPosition().getX();
        int finalAbscissa = targetPosition.getPosition().getX();
        int bigger = Integer.max(initialAbscissa, finalAbscissa);
        int smaller = Integer.min(initialAbscissa, finalAbscissa);

        for (int abscissa = smaller + 1; abscissa < bigger; abscissa++) {
            Position position = new Position( abscissa, actualPosition.getPosition().getY());
            TypeOfPiece typeOfPieceInCrossedPosition =
                    game.getChessboardPieceMap().get(Chessboard.valueOfPosition(position));
            if (!EMPTY.equals(typeOfPieceInCrossedPosition))
                return false;
        }
        return true;
    }


    private static boolean pieceIsAKnight(Game game, Chessboard actualPosition) {
        return WHITE_KNIGHT.equals(game.getChessboardPieceMap().get(actualPosition))
                || BLACK_KNIGHT.equals(game.getChessboardPieceMap().get(actualPosition));
    }


    private static void initBlackTeam(Game game) {
        game.getChessboardPieceMap().put(Chessboard.A8, BLACK_ROOK);
        game.getChessboardPieceMap().put(Chessboard.B8, BLACK_KNIGHT);
        game.getChessboardPieceMap().put(Chessboard.C8, BLACK_BISHOP);
        game.getChessboardPieceMap().put(Chessboard.D8, BLACK_QUEEN);
        game.getChessboardPieceMap().put(Chessboard.E8, BLACK_KING);
        game.getChessboardPieceMap().put(Chessboard.F8, BLACK_BISHOP);
        game.getChessboardPieceMap().put(Chessboard.G8, BLACK_KNIGHT);
        game.getChessboardPieceMap().put(Chessboard.H8, BLACK_ROOK);
        game.getChessboardPieceMap().put(Chessboard.A7, BLACK_PAWN);
        game.getChessboardPieceMap().put(Chessboard.B7, BLACK_PAWN);
        game.getChessboardPieceMap().put(Chessboard.C7, BLACK_PAWN);
        game.getChessboardPieceMap().put(Chessboard.D7, BLACK_PAWN);
        game.getChessboardPieceMap().put(Chessboard.E7, BLACK_PAWN);
        game.getChessboardPieceMap().put(Chessboard.F7, BLACK_PAWN);
        game.getChessboardPieceMap().put(Chessboard.G7, BLACK_PAWN);
        game.getChessboardPieceMap().put(Chessboard.H7, BLACK_PAWN);
    }

    private static void initWhiteTeam(Game game) {
        game.getChessboardPieceMap().put(Chessboard.A1, WHITE_ROOK);
        game.getChessboardPieceMap().put(Chessboard.B1, WHITE_KNIGHT);
        game.getChessboardPieceMap().put(Chessboard.C1, WHITE_BISHOP);
        game.getChessboardPieceMap().put(Chessboard.D1, WHITE_QUEEN);
        game.getChessboardPieceMap().put(Chessboard.E1, WHITE_KING);
        game.getChessboardPieceMap().put(Chessboard.F1, WHITE_BISHOP);
        game.getChessboardPieceMap().put(Chessboard.G1, WHITE_KNIGHT);
        game.getChessboardPieceMap().put(Chessboard.H1, WHITE_ROOK);
        game.getChessboardPieceMap().put(Chessboard.A2, WHITE_PAWN);
        game.getChessboardPieceMap().put(Chessboard.B2, WHITE_PAWN);
        game.getChessboardPieceMap().put(Chessboard.C2, WHITE_PAWN);
        game.getChessboardPieceMap().put(D2, WHITE_PAWN);
        game.getChessboardPieceMap().put(Chessboard.E2, WHITE_PAWN);
        game.getChessboardPieceMap().put(Chessboard.F2, WHITE_PAWN);
        game.getChessboardPieceMap().put(Chessboard.G2, WHITE_PAWN);
        game.getChessboardPieceMap().put(Chessboard.H2, WHITE_PAWN);
    }
}
