package com.angelolagreca.chess.domain;


import org.junit.jupiter.api.*;

import static com.angelolagreca.chess.domain.Chessboard.*;
import static com.angelolagreca.chess.domain.piece.TypeOfPiece.WHITE_KING;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KingMovementTest {

    private Chessboard actualPosition;
    private Chessboard targetPosition;
    private Movement movement;
    private final Game game = new Game();


    @Test
    void when_king_tries_move_without_change_position_it_should_return_false() {
        movement = new Movement(WHITE_KING, game);
        actualPosition = A1;
        targetPosition = A1;

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);
        assertFalse(actual);
    }

    @Test
    void kingMovement_should_return_false_when_moving_for_two_horizontal_case() {
        actualPosition = E8;
        targetPosition = C8;
        movement = new Movement(WHITE_KING, game);

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);

        assertFalse(actual);
    }

    @Test
    void kingMovement_should_return_false_whene_moving_for_two_vertical_case() {
        actualPosition = E8;
        targetPosition = E6;
        movement = new Movement(WHITE_KING, game);

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);

        assertFalse(actual);

    }

    @Test
    void kingMovement_should_return_true_when_there_is_a_single_diagonal_movement() {
        movement = new Movement(WHITE_KING, game);
        actualPosition = E8;
        targetPosition = D7;

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);

        assertTrue(actual);

    }

    @Test
    void should_return_false_when_white_king_move_from_D1_to_H5() {
        actualPosition = D1;
        targetPosition = H5;
        movement = new Movement(WHITE_KING, game);

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);

        assertFalse(actual);
    }

}