package com.angelolagreca.chess.domain;

import org.junit.jupiter.api.Test;

import static com.angelolagreca.chess.domain.Chessboard.*;
import static com.angelolagreca.chess.domain.piece.TypeOfPiece.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RookMovementTest {

    private Chessboard actualPosition;
    private Chessboard targetPosition;
    private Movement movement;
    private final Game game = new Game();

    @Test
    void should_be_false_when_white_rook_move_diagonally() {
        movement = new Movement(WHITE_ROOK, game);
        actualPosition = C1;
        targetPosition = A3;

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);

        assertFalse(actual);
    }

    @Test
    void should_be_true_when_white_rook_move_horizontally() {
        movement = new Movement(WHITE_ROOK, game);
        actualPosition = A1;
        targetPosition = A3;

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);

        assertTrue(actual);
    }

    @Test
    void should_be_true_when_when_white_rook_move_vertically() {
        movement = new Movement(WHITE_ROOK, game);
        actualPosition = C1;
        targetPosition = A1;

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);

        assertTrue(actual);
    }
    @Test
    void should_be_false_when_black_rook_move_diagonally() {
        movement = new Movement(BLACK_ROOK, game);
        actualPosition = A1;
        targetPosition = D5;

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);

        assertFalse(actual);
    }

    @Test
    void should_be_true_when_black_rook_move_horizontally() {
        movement = new Movement(BLACK_ROOK, game);
        actualPosition = A8;
        targetPosition = A1;

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);

        assertTrue(actual);
    }

    @Test
    void should_be_true_when_when_black_rook_move_vertically() {
        movement = new Movement(BLACK_ROOK, game);
        actualPosition = C1;
        targetPosition = A1;

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);

        assertTrue(actual);
    }



}
