package com.angelolagreca.chess.domain;

public enum OldPiece implements Move {

    KING,
    QUEEN,
    ROOK_1,
    ROOK_2,
    BISHOP_1,
    BISHOP_2,
    KNIGHT_1,
    KNIGHT_2,
    PAWN_1,
    PAWN_2,
    PAWN_3,
    PAWN_4,
    PAWN_5,
    PAWN_6,
    PAWN_7,
    PAWN_8,
    EMPTY;

    @Override
    public void move(OldPiece piece) {

    }
}
