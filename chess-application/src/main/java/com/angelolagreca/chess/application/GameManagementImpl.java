package com.angelolagreca.chess.application;

import com.angelolagreca.chess.domain.ChessboardPosition;
import com.angelolagreca.chess.domain.Game;
import com.angelolagreca.chess.domain.Movement;
import com.angelolagreca.chess.domain.piece.TypeOfPiece;
import org.springframework.stereotype.Component;

import static com.angelolagreca.chess.domain.piece.TypeOfPiece.*;


@Component
public class GameManagementImpl implements GameManagement {

    @Override
    public Game init()  {

        Game game = new Game();

        for (ChessboardPosition chessboardPosition : ChessboardPosition.values()) {
            game.getChessboardPieceMap().put(chessboardPosition, EMPTY);
        }
        initWhiteTeam(game);
        initBlackTeam(game);

        return game;
    }

    @Override
    public Game playerMove(Game game, ChessboardPosition oldPosition, ChessboardPosition newPosition) throws Exception {
        //determina nella vecchia posizione che piece c'é:
        TypeOfPiece pieceCheSiEdecisoDiMuovere = deterimaIlPezzoCheSiVuoleMuovere(game, oldPosition);
        //sposta nella nuova posizione
        muoviIlPezzo(game, oldPosition, newPosition, pieceCheSiEdecisoDiMuovere);
        //salva

        return game;
    }

    private static TypeOfPiece deterimaIlPezzoCheSiVuoleMuovere(Game game, ChessboardPosition oldPoistion) {
        return game.getChessboardPieceMap().get(oldPoistion);

    }

    private static void muoviIlPezzo(Game game, ChessboardPosition oldPoistion, ChessboardPosition newPosition,
                                     TypeOfPiece pieceCheSiEdecisoDiMuovere) throws Exception {
        checkIfMovimentIsPossibol(pieceCheSiEdecisoDiMuovere, oldPoistion, newPosition);
        game.getChessboardPieceMap().put(oldPoistion, EMPTY);
        game.getChessboardPieceMap().put(newPosition, pieceCheSiEdecisoDiMuovere);
    }

    private static void checkIfMovimentIsPossibol(TypeOfPiece pieceCheSiEdecisoDiMuovere, ChessboardPosition oldPoistion,
                                                  ChessboardPosition newPosition) throws Exception {
        Movement movement = new Movement(pieceCheSiEdecisoDiMuovere);
        if (!movement.isAllowed(oldPoistion, newPosition))
            throw new Exception("il movimento non é possibile");

    }


    private static void initBlackTeam(Game game) {
        game.getChessboardPieceMap().put(ChessboardPosition.A8, BLACK_ROOK);
        game.getChessboardPieceMap().put(ChessboardPosition.B8, BLACK_KNIGHT);
        game.getChessboardPieceMap().put(ChessboardPosition.C8, BLACK_BISHOP);
        game.getChessboardPieceMap().put(ChessboardPosition.D8, BLACK_QUEEN);
        game.getChessboardPieceMap().put(ChessboardPosition.E8, BLACK_KING);
        game.getChessboardPieceMap().put(ChessboardPosition.F8, BLACK_BISHOP);
        game.getChessboardPieceMap().put(ChessboardPosition.G8, BLACK_KNIGHT);
        game.getChessboardPieceMap().put(ChessboardPosition.H8, BLACK_ROOK);
        game.getChessboardPieceMap().put(ChessboardPosition.A7, BLACK_PAWN);
        game.getChessboardPieceMap().put(ChessboardPosition.B7, BLACK_PAWN);
        game.getChessboardPieceMap().put(ChessboardPosition.C7, BLACK_PAWN);
        game.getChessboardPieceMap().put(ChessboardPosition.D7, BLACK_PAWN);
        game.getChessboardPieceMap().put(ChessboardPosition.E7, BLACK_PAWN);
        game.getChessboardPieceMap().put(ChessboardPosition.F7, BLACK_PAWN);
        game.getChessboardPieceMap().put(ChessboardPosition.G7, BLACK_PAWN);
        game.getChessboardPieceMap().put(ChessboardPosition.H7, BLACK_PAWN);
    }

    private static void initWhiteTeam(Game game) {
        game.getChessboardPieceMap().put(ChessboardPosition.A1, WHITE_ROOK);
        game.getChessboardPieceMap().put(ChessboardPosition.B1, WHITE_KNIGHT);
        game.getChessboardPieceMap().put(ChessboardPosition.C1, WHITE_BISHOP);
        game.getChessboardPieceMap().put(ChessboardPosition.D1, WHITE_QUEEN);
        game.getChessboardPieceMap().put(ChessboardPosition.E1, WHITE_KING);
        game.getChessboardPieceMap().put(ChessboardPosition.F1, WHITE_BISHOP);
        game.getChessboardPieceMap().put(ChessboardPosition.G1, WHITE_KNIGHT);
        game.getChessboardPieceMap().put(ChessboardPosition.H1, WHITE_ROOK);
        game.getChessboardPieceMap().put(ChessboardPosition.A2, WHITE_PAWN);
        game.getChessboardPieceMap().put(ChessboardPosition.B2, WHITE_PAWN);
        game.getChessboardPieceMap().put(ChessboardPosition.C2, WHITE_PAWN);
        game.getChessboardPieceMap().put(ChessboardPosition.D2, WHITE_PAWN);
        game.getChessboardPieceMap().put(ChessboardPosition.E2, WHITE_PAWN);
        game.getChessboardPieceMap().put(ChessboardPosition.F2, WHITE_PAWN);
        game.getChessboardPieceMap().put(ChessboardPosition.G2, WHITE_PAWN);
        game.getChessboardPieceMap().put(ChessboardPosition.H2, WHITE_PAWN);
    }
}
