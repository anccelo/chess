package com.angelolagreca.chess.domain;

import org.junit.jupiter.api.Test;

import static com.angelolagreca.chess.domain.Chessboard.*;
import static com.angelolagreca.chess.domain.piece.TypeOfPiece.BLACK_BISHOP;
import static com.angelolagreca.chess.domain.piece.TypeOfPiece.WHITE_BISHOP;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BishopMovementTest {

    private Chessboard actualPosition;
    private Chessboard targetPosition;
    private Movement movement;
    private final Game game = new Game();

    @Test
    void should_be_false_when_white_bishop_move_horizontally() {
        movement = new Movement(WHITE_BISHOP, game);
        actualPosition = C1;
        targetPosition = C3;

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);

        assertFalse(actual);
    }

    @Test
    void should_be_false_when_white_bishop_move_vertically() {
        movement = new Movement(WHITE_BISHOP, game);
        actualPosition = C1;
        targetPosition = A1;

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);

        assertFalse(actual);
    }

    @Test
    void should_be_true_when_white_bishop_move_diagonally() {
        movement = new Movement(WHITE_BISHOP, game);
        actualPosition = C1;
        targetPosition = A3;

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);

        assertTrue(actual);
    }

    @Test
    void should_be_false_when_black_bishop_move_vertically() {
        movement = new Movement(BLACK_BISHOP, game);
        actualPosition = C1;
        targetPosition = A1;

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);

        assertFalse(actual);
    }

    @Test
    void should_be_true_when_black_bishop_move_diagonally() {
        movement = new Movement(BLACK_BISHOP, game);
        actualPosition = A3;
        targetPosition = B2;

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);

        assertTrue(actual);
    }


}
