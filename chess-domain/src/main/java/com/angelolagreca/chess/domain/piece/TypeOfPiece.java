package com.angelolagreca.chess.domain.piece;

import com.angelolagreca.chess.domain.vo.Color;

import static com.angelolagreca.chess.domain.vo.Color.*;

public enum TypeOfPiece {

    EMPTY(NO_COLOR),
    WHITE_KING(WHITE),
    BLACK_KING(BLACK),
    WHITE_QUEEN(WHITE),
    BLACK_QUEEN(BLACK),
    WHITE_BISHOP(WHITE),
    BLACK_BISHOP(BLACK),
    WHITE_KNIGHT(WHITE),
    BLACK_KNIGHT(BLACK),
    WHITE_ROOK(WHITE),
    BLACK_ROOK(BLACK),
    WHITE_PAWN(WHITE),
    BLACK_PAWN(BLACK);

    private final Color color;

    TypeOfPiece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
