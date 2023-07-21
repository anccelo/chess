package com.angelolagreca.chess.domain;

import com.angelolagreca.chess.domain.piece.TypeOfPiece;
import lombok.Getter;

import java.util.*;

import static com.angelolagreca.chess.domain.vo.Color.BLACK;
import static com.angelolagreca.chess.domain.vo.Color.WHITE;
import static com.angelolagreca.chess.domain.piece.TypeOfPiece.*;

@Getter
public class Game {

    private final String id = UUID.randomUUID().toString();

    private final Map<Chessboard, TypeOfPiece> chessboardPieceMap = new LinkedHashMap<>();
    private final Player whitePlayer = new Player(WHITE);
    private final Player blackPlayer = new Player(BLACK);

    List<TypeOfPiece> whitePlayerCaptureRegister = new ArrayList<>();
    List<TypeOfPiece> blackPlayerCaptureRegister = new ArrayList<>();

    private int loopMove = 1;
    private boolean flagWhitePlayerTurn = true;

    private final List<String> algebraicNotationRegister = new LinkedList<>();

    public Game() {

        for (Chessboard chessboard : Chessboard.values()) {
            this.chessboardPieceMap.put(chessboard, EMPTY);
        }
        initWhiteTeam();
        initBlackTeam();

    }

    public Integer incrementLoopMoveCounter() {
        return this.loopMove++;
    }

    public void setFlagWhitePlayerTurn(boolean flagWhitePlayerTurn) {
        this.flagWhitePlayerTurn = flagWhitePlayerTurn;
    }

    public List<String> getAlgebraicNotationRegister() {
        return algebraicNotationRegister;
    }

    public String printAlgebraicNotationRegister() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(algebraicNotationRegister.get(0) +" ");
        algebraicNotationRegister.stream().skip(1).forEach(notation->stringBuilder.append(notation +" "));
        return stringBuilder.toString().trim();

    }

    private void initBlackTeam() {
        this.chessboardPieceMap.put(Chessboard.A8, BLACK_ROOK);
        this.chessboardPieceMap.put(Chessboard.B8, BLACK_KNIGHT);
        this.chessboardPieceMap.put(Chessboard.C8, BLACK_BISHOP);
        this.chessboardPieceMap.put(Chessboard.D8, BLACK_QUEEN);
        this.chessboardPieceMap.put(Chessboard.E8, BLACK_KING);
        this.chessboardPieceMap.put(Chessboard.F8, BLACK_BISHOP);
        this.chessboardPieceMap.put(Chessboard.G8, BLACK_KNIGHT);
        this.chessboardPieceMap.put(Chessboard.H8, BLACK_ROOK);
        this.chessboardPieceMap.put(Chessboard.A7, BLACK_PAWN);
        this.chessboardPieceMap.put(Chessboard.B7, BLACK_PAWN);
        this.chessboardPieceMap.put(Chessboard.C7, BLACK_PAWN);
        this.chessboardPieceMap.put(Chessboard.D7, BLACK_PAWN);
        this.chessboardPieceMap.put(Chessboard.E7, BLACK_PAWN);
        this.chessboardPieceMap.put(Chessboard.F7, BLACK_PAWN);
        this.chessboardPieceMap.put(Chessboard.G7, BLACK_PAWN);
        this.chessboardPieceMap.put(Chessboard.H7, BLACK_PAWN);
    }

    private void initWhiteTeam() {
        this.chessboardPieceMap.put(Chessboard.A1, WHITE_ROOK);
        this.chessboardPieceMap.put(Chessboard.B1, WHITE_KNIGHT);
        this.chessboardPieceMap.put(Chessboard.C1, WHITE_BISHOP);
        this.chessboardPieceMap.put(Chessboard.D1, WHITE_QUEEN);
        this.chessboardPieceMap.put(Chessboard.E1, WHITE_KING);
        this.chessboardPieceMap.put(Chessboard.F1, WHITE_BISHOP);
        this.chessboardPieceMap.put(Chessboard.G1, WHITE_KNIGHT);
        this.chessboardPieceMap.put(Chessboard.H1, WHITE_ROOK);
        this.chessboardPieceMap.put(Chessboard.A2, WHITE_PAWN);
        this.chessboardPieceMap.put(Chessboard.B2, WHITE_PAWN);
        this.chessboardPieceMap.put(Chessboard.C2, WHITE_PAWN);
        this.chessboardPieceMap.put(Chessboard.D2, WHITE_PAWN);
        this.chessboardPieceMap.put(Chessboard.E2, WHITE_PAWN);
        this.chessboardPieceMap.put(Chessboard.F2, WHITE_PAWN);
        this.chessboardPieceMap.put(Chessboard.G2, WHITE_PAWN);
        this.chessboardPieceMap.put(Chessboard.H2, WHITE_PAWN);
    }
}
