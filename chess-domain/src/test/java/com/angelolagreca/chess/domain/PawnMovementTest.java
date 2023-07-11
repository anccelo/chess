package com.angelolagreca.chess.domain;

import org.junit.jupiter.api.Test;

import static com.angelolagreca.chess.domain.Chessboard.*;
import static com.angelolagreca.chess.domain.piece.TypeOfPiece.BLACK_PAWN;
import static com.angelolagreca.chess.domain.piece.TypeOfPiece.WHITE_PAWN;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PawnMovementTest {

    private Chessboard initialPoistion;
    private Chessboard finalPosition;
    private Movement movement;
    private final Game game = new Game();

    @Test
    void whitePawnMovement_should_return_false_when_stay_in_the_same_position() {
        movement = new Movement(WHITE_PAWN, game);
        initialPoistion = G6;
        finalPosition = G6;

        boolean actual = movement.isAllowed(initialPoistion, finalPosition);
        assertFalse(actual);
    }

    @Test
    void blackPawnMovement_should_return_false_when_stay_in_the_same_position() {
        movement = new Movement(BLACK_PAWN, game);
        initialPoistion = G2;
        finalPosition = G2;

        boolean actual = movement.isAllowed(initialPoistion, finalPosition);
        assertFalse(actual);
    }

    @Test
    void whitePawnMovement_should_return_false_when_moving_horizontally() {
        movement = new Movement(WHITE_PAWN, game);
        initialPoistion = E2;
        finalPosition = D2;

        boolean actual = movement.isAllowed(initialPoistion, finalPosition);
        assertFalse(actual);
    }

    @Test
    void whitePawnMovement_should_return_false_when_moving_vertically_for_two_position_from_not_start_position() {
        initialPoistion = D3;
        finalPosition = D5;
        movement = new Movement(WHITE_PAWN, game);

        boolean actual = movement.isAllowed(initialPoistion, finalPosition);
        assertFalse(actual);

    }

    @Test
    void whitePawnMovement_should_return_false_when_there_is_a_backward_movement() {
        initialPoistion = D3;
        finalPosition = D2;
        movement = new Movement(WHITE_PAWN, game);

        boolean actual = movement.isAllowed(initialPoistion, finalPosition);
        assertFalse(actual);
    }

    @Test
    void whitePawnMovement_should_return_false_when_moving_vertically_from_start_for_tree_position() {
        initialPoistion = D2;
        finalPosition = D5;

        movement = new Movement(WHITE_PAWN, game);

        boolean actual = movement.isAllowed(initialPoistion, finalPosition);
        assertFalse(actual);
    }

    @Test
    void whitePawnMovement_should_return_true_when_moving_just_vertically_for_only_one_position() {
        initialPoistion = D3;
        finalPosition = D4;

        movement = new Movement(WHITE_PAWN, game);

        boolean actual = movement.isAllowed(initialPoistion, finalPosition);
        assertTrue(actual);
    }

    @Test
    void whitePawnMovement_should_return_true_when_moving_vertically_from_start_for_two_position() {
        initialPoistion = D2;
        finalPosition = D4;

        movement = new Movement(WHITE_PAWN, game);

        boolean actual = movement.isAllowed(initialPoistion, finalPosition);
        assertTrue(actual);
    }


    @Test
    void BlackPawnMovement_should_return_false_when_moving_horizontally() {
        initialPoistion = D7;
        finalPosition = E7;
        movement = new Movement(BLACK_PAWN, game);

        boolean actual = movement.isAllowed(initialPoistion, finalPosition);
        assertFalse(actual);
    }

    @Test
    void blackPawnMovement_should_return_false_when_moving_vertically_for_two_position_from_not_start_position() {
        initialPoistion = D5;
        finalPosition = D3;
        movement = new Movement(BLACK_PAWN, game);

        boolean actual = movement.isAllowed(initialPoistion, finalPosition);
        assertFalse(actual);
    }

    @Test
    void blackPawnMovement_should_return_true_when_moving_just_vertically_for_only_one_position() {
        initialPoistion = D7;
        finalPosition = D6;
        movement = new Movement(BLACK_PAWN, game);

        boolean actual = movement.isAllowed(initialPoistion, finalPosition);
        assertTrue(actual);
    }

    @Test
    void blackPawnMovement_should_return_true_when_moving_vertically_from_start_for_two_position() {
        initialPoistion = B7;
        finalPosition = B5;

        movement = new Movement(BLACK_PAWN, game);

        boolean actual = movement.isAllowed(initialPoistion, finalPosition);
        assertTrue(actual);
    }


}
