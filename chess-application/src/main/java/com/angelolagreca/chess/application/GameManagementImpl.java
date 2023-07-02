package com.angelolagreca.chess.application;

import com.angelolagreca.chess.domain.ChessboardPosition;
import com.angelolagreca.chess.domain.Game;
import org.springframework.stereotype.Component;

import static com.angelolagreca.chess.domain.piece.TypeOfPiece.*;


@Component
public class GameManagementImpl implements GameManagement {

    @Override
    public Game init() {

        Game game = new Game();


        for (ChessboardPosition chessboardPosition : ChessboardPosition.values()
        ) {
            game.getChessboardPieceMap().put(chessboardPosition, EMPTY);
        }

        initWhiteTeam(game);
        initBlackTeam(game);

        return game;
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
