package com.angelolagreca.chess.domain;

import org.junit.jupiter.api.Test;

import static com.angelolagreca.chess.domain.Chessboard.*;
import static com.angelolagreca.chess.domain.piece.TypeOfPiece.BLACK_KNIGHT;
import static com.angelolagreca.chess.domain.piece.TypeOfPiece.WHITE_KNIGHT;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KnightMovementTest {

    private Chessboard actualPosition;
    private Chessboard targetPosition;
    private Movement movement;
    private final Game game = new Game();

    @Test
    void should_be_false_when_white_knight_try_move_to_same_position() {
        movement = new Movement(WHITE_KNIGHT, game);
        actualPosition = C1;
        targetPosition = C1;

        boolean actual = movement.isAllowed(actualPosition, targetPosition);

        assertFalse(actual);
    }
    @Test
    void should_be_false_when_white_knight_move_horizontally() {
        movement = new Movement(WHITE_KNIGHT, game);
        actualPosition = C1;
        targetPosition = A3;

        boolean actual = movement.isAllowed(actualPosition, targetPosition);

        assertFalse(actual);
    }

    @Test
    void should_be_false_when_white_knight_move_vertically() {
        movement = new Movement(WHITE_KNIGHT, game);
        actualPosition = C1;
        targetPosition = C2;

        boolean actual = movement.isAllowed(actualPosition, targetPosition);

        assertFalse(actual);
    }

    @Test
    void should_be_false_when_white_knight_move_diagonally() {
        movement = new Movement(WHITE_KNIGHT, game);
        actualPosition = B1;
        targetPosition = C2;

        boolean actual = movement.isAllowed(actualPosition, targetPosition);

        assertFalse(actual);
    }

    @Test
    void should_be_true_when_white_knight_move_twice_horizontally_and_once_vertically() {
        movement = new Movement(WHITE_KNIGHT, game);
        actualPosition = B1;
        targetPosition = A3;

        boolean actual = movement.isAllowed(actualPosition, targetPosition);

        assertTrue(actual);
    }

    @Test
    void should_be_true_when_white_knight_move_once_horizontally_and_twice_vertically() {
        movement = new Movement(WHITE_KNIGHT, game);
        actualPosition = A3;
        targetPosition = B1;

        boolean actual = movement.isAllowed(actualPosition, targetPosition);

        assertTrue(actual);
    }


    @Test
    void should_be_false_when_black_knight_move_diagonally() {
        movement = new Movement(BLACK_KNIGHT, game);
        actualPosition = H8;
        targetPosition = H6;

        boolean actual = movement.isAllowed(actualPosition, targetPosition);

        assertFalse(actual);
    }

}
