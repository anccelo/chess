package com.angelolagreca.chess.domain;

import org.junit.jupiter.api.Test;

import static com.angelolagreca.chess.domain.Chessboard.*;
import static com.angelolagreca.chess.domain.piece.TypeOfPiece.BLACK_PAWN;
import static com.angelolagreca.chess.domain.piece.TypeOfPiece.WHITE_PAWN;
import static org.junit.jupiter.api.Assertions.*;

class PawnMovementTest {

    private Chessboard actualPosition;
    private Chessboard targetPosition;
    private Movement movement;
    private final Game game = new Game();

    @Test
    void whitePawnMovement_should_return_false_when_stay_in_the_same_position() {
        movement = new Movement(WHITE_PAWN, game);
        actualPosition = G6;
        targetPosition = G6;

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);
        assertFalse(actual);
    }

    @Test
    void blackPawnMovement_should_return_false_when_stay_in_the_same_position() {
        movement = new Movement(BLACK_PAWN, game);
        actualPosition = G2;
        targetPosition = G2;

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);
        assertFalse(actual);
    }

    @Test
    void whitePawnMovement_should_return_false_when_moving_horizontally() {
        movement = new Movement(WHITE_PAWN, game);
        actualPosition = E2;
        targetPosition = D2;

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);
        assertFalse(actual);
    }

    @Test
    void blackPawnMovement_should_return_false_when_moving_horizontally() {
        actualPosition = D7;
        targetPosition = E7;
        movement = new Movement(BLACK_PAWN, game);

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);
        assertFalse(actual);
    }

    @Test
    void whitePawnMovement_should_return_false_when_moving_vertically_for_two_position_from_not_start_position() {
        actualPosition = D3;
        targetPosition = D5;
        movement = new Movement(WHITE_PAWN, game);

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);
        assertFalse(actual);

    }

    @Test
    void blackPawnMovement_should_return_false_when_moving_vertically_for_two_position_from_not_start_position() {
        actualPosition = D5;
        targetPosition = D3;
        movement = new Movement(BLACK_PAWN, game);

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);
        assertFalse(actual);
    }

    @Test
    void whitePawnMovement_should_return_false_when_there_is_a_backward_movement() {
        actualPosition = D3;
        targetPosition = D2;

        movement = new Movement(WHITE_PAWN, game);

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);
        assertFalse(actual);
    }

    @Test
    void blackPawnMovement_should_return_false_when_there_is_a_backward_movement() {
        actualPosition = G3;
        targetPosition = G4;
        movement = new Movement(BLACK_PAWN, game);

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);
        assertFalse(actual);
    }

    @Test
    void whitePawnMovement_should_return_false_when_moving_vertically_from_start_for_tree_position() {
        actualPosition = D2;
        targetPosition = D5;

        movement = new Movement(WHITE_PAWN, game);

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);
        assertFalse(actual);
    }

    @Test
    void blackPawnMovement_should_return_false_when_moving_vertically_from_start_for_tree_position() {
        actualPosition = D7;
        targetPosition = D4;

        movement = new Movement(WHITE_PAWN, game);

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);
        assertFalse(actual);
    }

    @Test
    void whitePawnMovement_should_return_false_when_target_vertically_position_is_occupied() {

        actualPosition = D6;
        targetPosition = D7;

        movement = new Movement(WHITE_PAWN, game);

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);
        assertFalse(actual);
    }

    @Test
    void blackPawnMovement_should_return_false_when_target_vertically_position_is_occupied() {

        actualPosition = A3;
        targetPosition = A2;

        movement = new Movement(BLACK_PAWN, game);

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);
        assertFalse(actual);
    }

    @Test
    void whitePawnMovement_should_return_true_when_target_diagonal_position_is_occupied_from_black_pawn() {

        actualPosition = A6;
        targetPosition = B7;

        movement = new Movement(WHITE_PAWN, game);

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);
        assertTrue(actual);
    }

    @Test
    void blackPawnMovement_should_return_true_when_target_diagonal_position_is_occupied_from_white_pawn() {

        actualPosition = A3;
        targetPosition = B2;

        movement = new Movement(BLACK_PAWN, game);

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);
        assertTrue(actual);
    }

    @Test
    void whitePawnMovement_should_return_true_when_moving_just_vertically_for_only_one_position() {
        actualPosition = D3;
        targetPosition = D4;

        movement = new Movement(WHITE_PAWN, game);

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);
        assertTrue(actual);
    }

    @Test
    void blackPawnMovement_should_return_true_when_moving_just_vertically_for_only_one_position() {
        actualPosition = A7;
        targetPosition = A6;
        movement = new Movement(BLACK_PAWN, game);

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);
        assertTrue(actual);
    }

    @Test
    void whitePawnMovement_should_return_true_when_moving_vertically_from_start_for_two_position() {
        actualPosition = D2;
        targetPosition = D4;

        movement = new Movement(WHITE_PAWN, game);

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);
        assertTrue(actual);
    }

    @Test
    void blackPawnMovement_should_return_true_when_moving_vertically_from_start_for_two_position() {
        actualPosition = B7;
        targetPosition = B5;

        movement = new Movement(BLACK_PAWN, game);

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);
        assertTrue(actual);
    }

    @Test
    void black_pawn_should_not_move_for_two_position_diagonal_from_start_position() {
        actualPosition = B7;
        targetPosition = D5;

        movement = new Movement(BLACK_PAWN, game);

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);
        assertFalse(actual);

    }

    @Test
    void black_pawn_should_not_capture_for_more_position_diagonal_from_start_position() {
        actualPosition = A7;
        targetPosition = F2;

        movement = new Movement(BLACK_PAWN, game);

        boolean actual = movement.isAllowed(game, actualPosition, targetPosition);
        assertFalse(actual);

    }


}
