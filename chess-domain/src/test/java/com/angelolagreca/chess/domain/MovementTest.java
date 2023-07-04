package com.angelolagreca.chess.domain;

import com.angelolagreca.chess.domain.piece.*;
import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;

import static com.angelolagreca.chess.domain.Chessboard.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(JUnit4.class)
public class MovementTest {

    private Chessboard initialPoistion;
    private Chessboard finalPosition;

    @Test
    public void when_king_tries_move_without_changing_position_it_should_return_false() {

        Movement movement = new Movement(TypeOfPiece.WHITE_KING);

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
        Movement movement = new Movement(TypeOfPiece.WHITE_KING);

        boolean actual = movement.isAllowed(initialPoistion, finalPosition);

        //act & assert
        assertFalse(actual);

    }

    @Test
    public void kingMovemet_should_return_false_whene_moving_for_two_vertical_case() {
    initialPoistion = E8;
    finalPosition = E6;
    Movement movement = new Movement(TypeOfPiece.WHITE_KING);

    boolean actual = movement.isAllowed(initialPoistion, finalPosition);

    //act & assert
    assertFalse(actual);

    }

//    @Test
//    public void kingMovemet_should_return_false_whene_initial_and_new_positions_are_same() {
//        //Arrange
//        initialPosition = new Position('A', 1);
//        finalPosition = new Position('A', 1);
//
//        //act & assert
////        assertFalse(Movement.kingMovemet(initialPosition, finalPosition));
//
//    }
//
//    @Test
//    public void kingMovemet_should_return_true_whene_there_is_a_single_diagnoal_movement() {
//        //Arrange
//        initialPosition = new Position('A', 1);
//        finalPosition = new Position('B', 2);
//
//        //act & assert
////        assertTrue(Movement.kingMovemet(initialPosition, finalPosition));
//
//    }
//
//    @Test
//    public void WhitePawnMovemet_should_return_false_when_moving_horizontally() {
//        //Arrange
//        initialPosition = new Position('B', 3);
//        finalPosition = new Position('C', 3);
//
//        //act & assert
////        assertFalse(Movement.WhitePawnMovement(initialPosition, finalPosition));
//    }
//
//    @Test
//    public void WhitePawnMovemet_should_return_false_when_moving_verticaly_for_two_position_but_true_for_initialPosition() {
//        //Arrange
//        initialPosition = new Position('D', 2);
//        finalPosition = new Position('D', 4);
//
//        //act & assert
////        assertFalse(Movement.WhitePawnMovement(initialPosition, finalPosition));
////        assertTrue(Movement.initialWhitePawnMovement(initialPosition, finalPosition));
//    }
//
//    @Test
//    public void WhitePawnMovemet_should_return_false_when_there_is_a_backward_movement() {
//        //Arrange
//        initialPosition = new Position('C', 4);
//        finalPosition = new Position('C', 3);
//        //act & assert
////        assertFalse(Movement.WhitePawnMovement(initialPosition, finalPosition));
//    }
//
//    @Test
//    public void WhitePawnMovemet_should_return_true_when_moving_just_verticaly_for_only_one_position() {
//        //Arrange
//        initialPosition = new Position('D', 3);
//        finalPosition = new Position('D', 4);
//        //act & assert
////        assertTrue(Movement.WhitePawnMovement(initialPosition, finalPosition));
////        assertTrue(Movement.initialWhitePawnMovement(initialPosition, finalPosition));
//    }
//
//    @Test
//    public void BlackPawnMovemet_should_return_false_when_moving_horizontally() {
//        //Arrange
//        initialPosition = new Position('E', 7);
//        finalPosition = new Position('D', 7);
//
//        //act & assert
////        assertFalse(Movement.BlackPawnMovement(initialPosition, finalPosition));
//    }
//
//    @Test
//    public void BlackPawnMovemet_should_return_true_when_moving_just_verticaly_for_only_one_position() {
//        //Arrange
//        initialPosition = new Position('D', 7);
//        finalPosition = new Position('D', 6);
//
//        //act & assert
////        assertTrue(Movement.BlackPawnMovement(initialPosition, finalPosition));
//    }
//
//    @Test
//    public void BlackPawnMovemet_should_return_false_when_moving_verticaly_for_two_position() {
//        //Arrange
//        initialPosition = new Position('D', 7);
//        finalPosition = new Position('D', 5);
//
//        //act & assert
////        assertFalse(Movement.BlackPawnMovement(initialPosition, finalPosition));
//    }
//
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
