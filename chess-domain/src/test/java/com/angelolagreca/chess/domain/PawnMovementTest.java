package com.angelolagreca.chess.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.angelolagreca.chess.domain.Chessboard.*;
import static com.angelolagreca.chess.domain.piece.TypeOfPiece.BLACK_PAWN;
import static com.angelolagreca.chess.domain.piece.TypeOfPiece.WHITE_PAWN;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(JUnit4.class)
public class PawnMovementTest {

    private Chessboard initialPoistion;
    private Chessboard finalPosition;
    private Movement movement;

    @Test
    public void whitePawnMovement_should_return_false_when_stay_in_the_same_position() {
        movement = new Movement(WHITE_PAWN);
        initialPoistion = G6;
        finalPosition = G6;

        boolean actual = movement.isAllowed(initialPoistion, finalPosition);
        assertFalse(actual);
    }

    @Test
    public void whitePawnMovement_should_return_false_when_moving_horizontally() {
        movement = new Movement(WHITE_PAWN);
        initialPoistion = E2;
        finalPosition = D2;

        boolean actual = movement.isAllowed(initialPoistion, finalPosition);
        assertFalse(actual);
    }

    @Test
    public void whitePawnMovement_should_return_false_when_moving_vertically_for_two_position_from_not_start_position() {
        initialPoistion = D3;
        finalPosition = D5;
        movement = new Movement(WHITE_PAWN);

        boolean actual = movement.isAllowed(initialPoistion, finalPosition);
        assertFalse(actual);

    }

    @Test
    public void whitePawnMovement_should_return_false_when_there_is_a_backward_movement() {
        initialPoistion = D3;
        finalPosition = D2;
        movement = new Movement(WHITE_PAWN);

        boolean actual = movement.isAllowed(initialPoistion, finalPosition);
        assertFalse(actual);
    }

    @Test
    public void whitePawnMovement_should_return_false_when_moving_vertically_from_start_for_tree_position() {
        initialPoistion = D2;
        finalPosition = D5;

        movement = new Movement(WHITE_PAWN);

        boolean actual = movement.isAllowed(initialPoistion, finalPosition);
        assertFalse(actual);
    }

    @Test
    public void whitePawnMovement_should_return_true_when_moving_just_vertically_for_only_one_position() {
        initialPoistion = D3;
        finalPosition = D4;

        movement = new Movement(WHITE_PAWN);

        boolean actual = movement.isAllowed(initialPoistion, finalPosition);
        assertTrue(actual);
    }

    @Test
    public void whitePawnMovement_should_return_true_when_moving_vertically_from_start_for_two_position() {
        initialPoistion = D2;
        finalPosition = D4;

        movement = new Movement(WHITE_PAWN);

        boolean actual = movement.isAllowed(initialPoistion, finalPosition);
        assertTrue(actual);
    }


    @Test
    public void BlackPawnMovement_should_return_false_when_moving_horizontally() {
        initialPoistion = D7;
        finalPosition = E7;
        movement = new Movement(BLACK_PAWN);

        boolean actual = movement.isAllowed(initialPoistion, finalPosition);
        assertFalse(actual);
    }

    @Test
    public void blackPawnMovement_should_return_false_when_moving_vertically_for_two_position_from_not_start_position() {
        initialPoistion = D5;
        finalPosition = D3;
        movement = new Movement(BLACK_PAWN);

        boolean actual = movement.isAllowed(initialPoistion, finalPosition);
        assertFalse(actual);
    }

    @Test
    public void blackPawnMovement_should_return_true_when_moving_just_vertically_for_only_one_position() {
        initialPoistion = D7;
        finalPosition = D6;
        movement = new Movement(BLACK_PAWN);

        boolean actual = movement.isAllowed(initialPoistion, finalPosition);
        assertTrue(actual);
    }

    @Test
    public void blackPawnMovement_should_return_true_when_moving_vertically_from_start_for_two_position() {
        initialPoistion = B7;
        finalPosition = B5;

        movement = new Movement(BLACK_PAWN);

        boolean actual = movement.isAllowed(initialPoistion, finalPosition);
        assertTrue(actual);
    }


//    @Test
//    public void BlackPawnMovemet_should_return_true_when_moving_verticaly_for_two_position_only_from_start_game_postion() {
//        //Arrange
//        Position goodInitialPosition = new Position('D', 7);
//        Position finalPosition1 = new Position('D', 5);
//        Position NoGoodInitialPosition = new Position('D', 4);
//        Position finalPosition2 = new Position('D', 2);
//
//        //act & assert
////        assertTrue(Movement.initialBlackPawnMovement(goodInitialPosition, finalPosition1));
////        assertFalse(Movement.initialBlackPawnMovement(NoGoodInitialPosition, finalPosition2));
//    }
//
//
//    @Test
//    public void bishopMovemet_should_return_true_only_when_moving_diagonally() {
//        //Arrange
//        initialPosition = new Position('C', 1);
//        finalPosition = new Position('H', 6);
//        Position noGoodFinalPosition = new Position('H', 5);
//        Position F3 = new Position('F', 3);
//        Position A8 = new Position('A', 8);
//
//
//        //act & assert
////        assertTrue(Movement.bishopMovemet(initialPosition, finalPosition));
////        assertTrue(Movement.bishopMovemet(F3, A8));
////        assertTrue(Movement.bishopMovemet(A8, F3));
////        assertFalse(Movement.bishopMovemet(initialPosition, noGoodFinalPosition));
////        assertFalse(Movement.bishopMovemet(initialPosition, A8));
//    }
//
//    @Test
//    public void rookMovemet_should_return_true_when_moving_orizontaly() {
//        //Arrange
//        Position F3 = new Position('F', 3);
//        Position A3 = new Position('A', 3);
//        Position A8 = new Position('A', 8);
//        Position B7 = new Position('B', 7);
//
////        assertTrue(Movement.rookMovemet(F3, A3));
////        assertTrue(Movement.rookMovemet( A3 ,F3 ));
////        assertFalse(Movement.rookMovemet(A8, B7));
////        assertFalse(Movement.rookMovemet( B7,A8));
//    }
//
//    @Test
//    public void rookMovemet_should_return_true_when_moving_verticaly() {
//        //Arrange
//        Position F3 = new Position('F', 3);
//        Position F5 = new Position('F', 5);
//        Position A3 = new Position('A', 3);
//        Position A8 = new Position('A', 8);
//        Position B7 = new Position('B', 7);
//
////        assertTrue(Movement.rookMovemet(F3, F5));
////        assertTrue(Movement.rookMovemet( F5 ,F3 ));
////        assertTrue(Movement.rookMovemet( A3 ,A8 ));
////        assertFalse(Movement.rookMovemet(A8, B7));
////        assertFalse(Movement.rookMovemet( B7,A8));
//    }
//
//    @Test
//    public void knight_should_return_true_when_it_makes_an_L_shaped_move() {
//
//        //Arrange
//        Position B1 = new Position('B', 1);
//        Position A3 = new Position('A', 3);
//        Position C3 = new Position('C', 3);
//        Position D2 = new Position('D', 2);
//        Position F5 = new Position('F', 5);
//
//
////        assertTrue(Movement.knightMovemet(B1, A3));
////        assertTrue(Movement.knightMovemet(B1, C3));
////        assertTrue(Movement.knightMovemet(B1, D2));
////        assertTrue(Movement.knightMovemet(D2, B1));
////        assertTrue(Movement.knightMovemet(C3, B1));
////        assertTrue(Movement.knightMovemet(A3, B1));
////        assertFalse(Movement.knightMovemet(F5 ,A3));
//
//    }
//
//
//


}
