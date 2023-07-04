package com.angelolagreca.chess.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.angelolagreca.chess.domain.Chessboard.*;
import static com.angelolagreca.chess.domain.piece.TypeOfPiece.WHITE_KING;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(JUnit4.class)
public class KingMovementTest {

    private Chessboard initialPoistion;
    private Chessboard finalPosition;
    private Movement movement;

    @Test
    public void when_king_tries_move_without_changing_position_it_should_return_false() {

        movement = new Movement(WHITE_KING);

        initialPoistion = A1;
        finalPosition = A1;

        boolean actual = movement.isAllowed(initialPoistion, finalPosition);
        assertFalse(actual);

    }

    @Test
    public void kingMovement_should_return_false_when_moving_for_two_horizontal_case() {
        //Arrange
        initialPoistion = E8;
        finalPosition = C8;
        movement = new Movement(WHITE_KING);

        boolean actual = movement.isAllowed(initialPoistion, finalPosition);

        //act & assert
        assertFalse(actual);

    }

    @Test
    public void kingMovement_should_return_false_whene_moving_for_two_vertical_case() {
        initialPoistion = E8;
        finalPosition = E6;
        movement = new Movement(WHITE_KING);

        boolean actual = movement.isAllowed(initialPoistion, finalPosition);

        //act & assert
        assertFalse(actual);

    }


    @Test
    public void kingMovement_should_return_true_when_there_is_a_single_diagonal_movement() {
        movement = new Movement(WHITE_KING);
        initialPoistion = E8;
        finalPosition = D7;

        boolean actual = movement.isAllowed(initialPoistion, finalPosition);
        assertTrue(actual);

    }

}