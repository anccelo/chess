package com.angelolagreca.chess.domain;

import lombok.Getter;

@Getter
public enum ChessboardPosition {

    A1(1, 1), B1(2,1), C1(3,1),D1(4,1),E1(5,1),F1(6,1),G1(7,1),H1(8,1),
    A2(1, 2), B2(2,2), C2(3,2),D2(4,2),E2(5,2),F2(6,2),G2(7,2),H2(8,2),
    A3(1, 3), B3(2,3), C3(3,3),D3(4,3),E3(5,3),F3(6,3),G3(7,3),H3(8,3),
    A4(1, 4), B4(2,4), C4(3,4),D4(4,4),E4(5,4),F4(6,4),G4(7,4),H4(8,4),
    A5(1, 5), B5(2,5), C5(3,5),D5(4,5),E5(5,5),F5(6,5),G5(7,5),H5(8,5),
    A6(1, 6), B6(2,6), C6(3,6),D6(4,6),E6(5,6),F6(6,6),G6(7,6),H6(8,6),
    A7(1, 7), B7(2,7), C7(3,7),D7(4,7),E7(5,7),F7(6,7),G7(7,7),H7(8,7),
    A8(1, 8), B8(2,8), C8(3,8),D8(4,8),E8(5,8),F8(6,8),G8(7,8),H8(8,8);

    private final int x;
    private final int y;

    ChessboardPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}