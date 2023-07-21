package com.angelolagreca.chess.domain.piece;

import com.angelolagreca.chess.domain.vo.Color;

import static com.angelolagreca.chess.domain.vo.Color.*;

public enum TypeOfPiece {

    EMPTY(NO_COLOR, ""),
    WHITE_KING(WHITE, "K"),
    BLACK_KING(BLACK, "K"),
    WHITE_QUEEN(WHITE, "Q"),
    BLACK_QUEEN(BLACK, "Q"),
    WHITE_BISHOP(WHITE, "B"),
    BLACK_BISHOP(BLACK, "B"),
    WHITE_KNIGHT(WHITE, "N"),
    BLACK_KNIGHT(BLACK, "N"),
    WHITE_ROOK(WHITE, "R"),
    BLACK_ROOK(BLACK, "R"),
    WHITE_PAWN(WHITE, ""),
    BLACK_PAWN(BLACK, "");

    private final Color color;
    private final String algebraicNotationName;

    TypeOfPiece(Color color, String algebraicNotationName) {
        this.color = color;
        this.algebraicNotationName = algebraicNotationName;
    }

    public Color getColor() {
        return color;
    }


    public String getAlgebraicNotationName() {
        return algebraicNotationName;
    }

}
