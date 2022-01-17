package com.angelolagreca.chess.domain;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(JUnit4.class)
class MovementTest {

    private Position initialPosition;
    private Position finalPosition;

    @Test
    void moved_should_return_false_if_there_is_not_chang_of_position(){
        initialPosition = new Position('A', 3);
        finalPosition = new Position('A', 3);

        assertFalse(Movement.moved(initialPosition, finalPosition));

    }

    @Test
    void kingMovemet_should_return_false_whene_moving_for_two_orizontal_case() {
        //Arrange
        initialPosition = new Position('A', 3);
        finalPosition = new Position('C', 3);

        //act & assert
        assertFalse(Movement.kingMovemet(initialPosition, finalPosition));

    }

    @Test
    void kingMovemet_should_return_false_whene_moving_for_two_vertical_case() {
        //Arrange
        initialPosition = new Position('A', 1);
        finalPosition = new Position('A', 3);

        //act & assert
        assertFalse(Movement.kingMovemet(initialPosition, finalPosition));

    }

    @Test
    void kingMovemet_should_return_false_whene_initial_and_new_positions_are_same() {
        //Arrange
        initialPosition = new Position('A', 1);
        finalPosition = new Position('A', 1);

        //act & assert
        assertFalse(Movement.kingMovemet(initialPosition, finalPosition));

    }

    void kingMovemet_should_return_true_whene_there_is_a_single_diagnoal_movement() {
        //Arrange
        initialPosition = new Position('A', 1);
        finalPosition = new Position('B', 2);

        //act & assert
        assertTrue(Movement.kingMovemet(initialPosition, finalPosition));

    }

    @Test
    void pawnMovemet_should_return_false_when_moving_horizontally() {
        //Arrange
        initialPosition = new Position('B', 3);
        finalPosition = new Position('C', 3);

        //act & assert
        assertFalse(Movement.WhitePawnMovement(initialPosition, finalPosition));
    }
    @Test
    void pawnMovemet_should_return_false_when_moving_verticaly_for_two_position_but_true_for_initialPosition() {
        //Arrange
        initialPosition = new Position('D', 2);
        finalPosition = new Position('D', 4);

        //act & assert
        assertFalse(Movement.WhitePawnMovement(initialPosition, finalPosition));
        assertTrue(Movement.initialWhitePawnMovement(initialPosition, finalPosition));
    }
    @Test
    void pawnMovemet_should_return_false_when_there_is_a_backward_movement() {
        //Arrange
        initialPosition = new Position('C', 4);
        finalPosition = new Position('C', 3);
        //act & assert
        assertFalse(Movement.WhitePawnMovement(initialPosition, finalPosition));
    }

    @Test
    void pawnMovemet_should_return_true_when_moving_just_verticaly_for_only_one_position() {
        //Arrange
        initialPosition = new Position('D', 3);
        finalPosition = new Position('D', 4);
        //act & assert
        assertTrue(Movement.WhitePawnMovement(initialPosition, finalPosition));
        assertTrue(Movement.initialWhitePawnMovement(initialPosition, finalPosition));
    }



}
