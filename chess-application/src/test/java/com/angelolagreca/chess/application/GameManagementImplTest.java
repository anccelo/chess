package com.angelolagreca.chess.application;

import com.angelolagreca.chess.domain.Game;
import com.angelolagreca.chess.domain.exception.PieceMovementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.angelolagreca.chess.domain.Chessboard.*;
import static com.angelolagreca.chess.domain.piece.TypeOfPiece.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = GameManagementImpl.class)
class GameManagementImplTest {

    public static final String MOVEMENT_NOT_ALLOWED = "il movimento non é possibile";
    @Autowired
    GameManagementImpl gameManagement;

    @Test
    void chessboard_should_have_initial_configuration_of_piece_when_a_game_is_initialised() {
        Game actual = gameManagement.init();

        assertEquals(WHITE_KING, actual.getChessboardPieceMap().get(E1));
        assertEquals(WHITE_ROOK, actual.getChessboardPieceMap().get(A1));
        assertEquals(BLACK_KNIGHT, actual.getChessboardPieceMap().get(B8));
        assertEquals(BLACK_PAWN, actual.getChessboardPieceMap().get(D7));
    }

    @Test
    void should_be_returned_actua_game_when_target_position_is_not_occupied_for_a_piece_of_same_color()
            throws PieceMovementException {

        Game game = gameManagement.init();
        Game movePawn = gameManagement.playerMove(game, D7, D6);
        Game moveAgainPawn = gameManagement.playerMove(movePawn, D6, D5);
        Game actualMoveQueen = gameManagement.playerMove(moveAgainPawn, D8, D6);

        Assertions.assertInstanceOf(Game.class, actualMoveQueen);

    }

    @Test
    void a_PieceMovementException_should_be_throw_when_target_position_is_occupied_for_a_piece_of_same_color_() {

        Game game = gameManagement.init();

        PieceMovementException thrown = Assertions.assertThrows(PieceMovementException.class, () ->
                gameManagement.playerMove(game, E8, E7)
        );

        Assertions.assertEquals(MOVEMENT_NOT_ALLOWED, thrown.getMessage());

    }

    @Test
    void queen_right_horizontal_movement_should_not_permitted_when_an_other_piece_is_present_between_actual_and_target_position() throws PieceMovementException {

        Game game = gameManagement.init();
        Game movePawnFromD2 = gameManagement.playerMove(game, D2, D3);
        Game movePawnFromC2 = gameManagement.playerMove(movePawnFromD2, E2, E3);
        Game movePawnFromA2 = gameManagement.playerMove(movePawnFromC2, G2, G3);
        Game actualMoveQueen = gameManagement.playerMove(movePawnFromA2, D1, D2);

        assertEquals(WHITE_QUEEN, actualMoveQueen.getChessboardPieceMap().get(D2));
        assertEquals(WHITE_PAWN, actualMoveQueen.getChessboardPieceMap().get(B2));
        PieceMovementException thrown = Assertions.assertThrows(PieceMovementException.class, () ->
                gameManagement.playerMove(actualMoveQueen, D2, H2));
        assertEquals(MOVEMENT_NOT_ALLOWED, thrown.getMessage());

    }

    @Test
    void queen_left_horizontal_movement_should_not_permitted_when_an_other_piece_is_present_between_actual_and_target_position() throws PieceMovementException {

        Game game = gameManagement.init();
        Game movePawnFromD2 = gameManagement.playerMove(game, D2, D3);
        Game movePawnFromC2 = gameManagement.playerMove(movePawnFromD2, C2, C3);
        Game movePawnFromA2 = gameManagement.playerMove(movePawnFromC2, A2, A3);
        Game actualMoveQueen = gameManagement.playerMove(movePawnFromA2, D1, D2);

        assertEquals(WHITE_QUEEN, actualMoveQueen.getChessboardPieceMap().get(D2));
        assertEquals(WHITE_PAWN, actualMoveQueen.getChessboardPieceMap().get(B2));
        PieceMovementException thrown = Assertions.assertThrows(PieceMovementException.class, () ->
                gameManagement.playerMove(actualMoveQueen, D2, A2));
        assertEquals(MOVEMENT_NOT_ALLOWED, thrown.getMessage());

    }

    @Test
    void queen_up_vertically_movement_should_not_permitted_when_an_other_piece_is_present_between_actual_and_target_position() {

        Game game = gameManagement.init();

        PieceMovementException thrown = Assertions.assertThrows(PieceMovementException.class, () ->
                gameManagement.playerMove(game, D1, D4));

        assertEquals(WHITE_QUEEN, game.getChessboardPieceMap().get(D1));
        assertEquals(MOVEMENT_NOT_ALLOWED, thrown.getMessage());

    }

    @Test
    void queen_down_vertically_movement_should_not_permitted_when_an_other_piece_is_present_between_actual_and_target_position() {

        Game game = gameManagement.init();

        PieceMovementException thrown = Assertions.assertThrows(PieceMovementException.class, () ->
                gameManagement.playerMove(game, D8, D5));

        assertEquals(BLACK_QUEEN, game.getChessboardPieceMap().get(D8));
        assertEquals(MOVEMENT_NOT_ALLOWED, thrown.getMessage());

    }

}