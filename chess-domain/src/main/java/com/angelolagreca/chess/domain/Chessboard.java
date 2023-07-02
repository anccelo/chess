package com.angelolagreca.chess.domain;


import com.angelolagreca.chess.domain.piece.TypeOfPiece;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.angelolagreca.chess.domain.piece.TypeOfPiece.EMPTY;

@Getter
public class Chessboard {

    private final Map<ChessboardPosition, TypeOfPiece> cheesboard = new LinkedHashMap<>();

    public Chessboard() {
        for (ChessboardPosition chessboardEnum : ChessboardPosition.values()
        ) {
            cheesboard.put(chessboardEnum, EMPTY);
        }
    }


}
