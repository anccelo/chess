package com.angelolagreca.chess.domain;

import com.angelolagreca.chess.domain.exception.UnexpectedError;
import com.angelolagreca.chess.domain.piece.TypeOfPiece;
import com.angelolagreca.chess.domain.vo.Position;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum Chessboard {

    A1(new Position(1, 1)), B1(new Position(2, 1)), C1(new Position(3, 1)), D1(new Position(4, 1)), E1(new Position(5, 1)), F1(new Position(6, 1)), G1(new Position(7, 1)), H1(new Position(8, 1)),
    A2(new Position(1, 2)), B2(new Position(2, 2)), C2(new Position(3, 2)), D2(new Position(4, 2)), E2(new Position(5, 2)), F2(new Position(6, 2)), G2(new Position(7, 2)), H2(new Position(8, 2)),
    A3(new Position(1, 3)), B3(new Position(2, 3)), C3(new Position(3, 3)), D3(new Position(4, 3)), E3(new Position(5, 3)), F3(new Position(6, 3)), G3(new Position(7, 3)), H3(new Position(8, 3)),
    A4(new Position(1, 4)), B4(new Position(2, 4)), C4(new Position(3, 4)), D4(new Position(4, 4)), E4(new Position(5, 4)), F4(new Position(6, 4)), G4(new Position(7, 4)), H4(new Position(8, 4)),
    A5(new Position(1, 5)), B5(new Position(2, 5)), C5(new Position(3, 5)), D5(new Position(4, 5)), E5(new Position(5, 5)), F5(new Position(6, 5)), G5(new Position(7, 5)), H5(new Position(8, 5)),
    A6(new Position(1, 6)), B6(new Position(2, 6)), C6(new Position(3, 6)), D6(new Position(4, 6)), E6(new Position(5, 6)), F6(new Position(6, 6)), G6(new Position(7, 6)), H6(new Position(8, 6)),
    A7(new Position(1, 7)), B7(new Position(2, 7)), C7(new Position(3, 7)), D7(new Position(4, 7)), E7(new Position(5, 7)), F7(new Position(6, 7)), G7(new Position(7, 7)), H7(new Position(8, 7)),
    A8(new Position(1, 8)), B8(new Position(2, 8)), C8(new Position(3, 8)), D8(new Position(4, 8)), E8(new Position(5, 8)), F8(new Position(6, 8)), G8(new Position(7, 8)), H8(new Position(8, 8));

    private static final Map<Position, Chessboard> BY_POSITION = new HashMap<>();


    private final Position position;

    Chessboard(Position position) {
        this.position = position;

    }

    public static Chessboard valueOfPosition(Position position) {
        for (Chessboard chessboardNameCase : values()) {
            if (chessboardNameCase.position.equals(position)) {
                return chessboardNameCase;
            }
        }
        throw new EnumConstantNotPresentException(Chessboard.class,null);
    }



}
