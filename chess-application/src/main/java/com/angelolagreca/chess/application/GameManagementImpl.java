package com.angelolagreca.chess.application;

import com.angelolagreca.chess.domain.Chessboard;
import com.angelolagreca.chess.domain.Game;
import com.angelolagreca.chess.domain.Movement;
import com.angelolagreca.chess.domain.exception.PieceMovementException;
import com.angelolagreca.chess.domain.piece.TypeOfPiece;
import org.springframework.stereotype.Component;

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
    public Game playerMove(Game game, Chessboard oldPosition, Chessboard newPosition) throws PieceMovementException {

        TypeOfPiece pieceCheSiEdecisoDiMuovere = determinaIlPezzoCheSiVuoleMuovere(game, oldPosition);
        moveThePiece(game, oldPosition, newPosition, pieceCheSiEdecisoDiMuovere);
        return game;
    }

    private static TypeOfPiece determinaIlPezzoCheSiVuoleMuovere(Game game, Chessboard oldPoistion) {
        return game.getChessboardPieceMap().get(oldPoistion);

    }

    private static void moveThePiece(Game game, Chessboard oldPoistion, Chessboard newPosition,
                                     TypeOfPiece pieceCheSiEdecisoDiMuovere) throws PieceMovementException {
        checkIfMovementIsPossible(pieceCheSiEdecisoDiMuovere, game, oldPoistion, newPosition);
        game.getChessboardPieceMap().put(oldPoistion, EMPTY);
        game.getChessboardPieceMap().put(newPosition, pieceCheSiEdecisoDiMuovere);
    }

    private static void checkIfMovementIsPossible(TypeOfPiece pieceCheSiEdecisoDiMuovere, Game game, Chessboard oldPoistion,
                                                  Chessboard targetPoition) throws PieceMovementException {
        Movement movement = new Movement(pieceCheSiEdecisoDiMuovere, game);

        if (movement.isTargetPositionOccupiedByAPieceOfItsOwnColour(oldPoistion, targetPoition)
                || !movement.isAllowed(oldPoistion, targetPoition)) {

            throw new PieceMovementException("il movimento non é possibile");
        }

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
        game.getChessboardPieceMap().put(Chessboard.D2, WHITE_PAWN);
        game.getChessboardPieceMap().put(Chessboard.E2, WHITE_PAWN);
        game.getChessboardPieceMap().put(Chessboard.F2, WHITE_PAWN);
        game.getChessboardPieceMap().put(Chessboard.G2, WHITE_PAWN);
        game.getChessboardPieceMap().put(Chessboard.H2, WHITE_PAWN);
    }
}
