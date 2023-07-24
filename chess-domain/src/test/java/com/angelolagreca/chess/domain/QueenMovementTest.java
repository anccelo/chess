package com.angelolagreca.chess.domain;


import org.junit.jupiter.api.Test;

import static com.angelolagreca.chess.domain.Chessboard.*;
import static com.angelolagreca.chess.domain.piece.TypeOfPiece.BLACK_QUEEN;
import static com.angelolagreca.chess.domain.piece.TypeOfPiece.WHITE_QUEEN;
import static org.junit.jupiter.api.Assertions.assertTrue;


class QueenMovementTest {

    private Chessboard actualPosition;
    private Chessboard targetPosition;
    private final Game game = new Game();
    private Movement movement;

    @Test
    void should_return_true_when_black_queen_move_horizontally() {
        actualPosition = D1;
        targetPosition = D8;
        movement = new Movement(BLACK_QUEEN, game);

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);

        assertTrue(actual);
    }

    @Test
    void should_return_true_when_black_queen_move_in_diagonal() {
        actualPosition = D1;
        targetPosition = H5;
        movement = new Movement(BLACK_QUEEN, game);

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);

        assertTrue(actual);
    }

    @Test
    void should_return_true_when_black_queen_move_vertically() {
        actualPosition = H5;
        targetPosition = B5;
        movement = new Movement(BLACK_QUEEN, game);

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);

        assertTrue(actual);
    }

    @Test
    void should_return_true_when_White_queen_move_one_position_horizontally() {
        actualPosition = D4;
        targetPosition = D3;
        movement = new Movement(WHITE_QUEEN, game);

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);

        assertTrue(actual);
    }


}