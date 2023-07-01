package com.angelolagreca.chess.domain;


import com.angelolagreca.chess.domain.piece.PieceEnum;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.angelolagreca.chess.domain.piece.PieceEnum.EMPTY;

@Getter
public class Chessboard {

    private final Map<PositionEnum, PieceEnum> cheesboard = new LinkedHashMap<>();

    public Chessboard() {
        for (PositionEnum positionEnum : PositionEnum.values()
        ) {
            cheesboard.put(positionEnum, EMPTY);
        }
    }


}
