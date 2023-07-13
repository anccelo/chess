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

    public static final String MOVEMENT_NOT_ALLOWED = "Movement is not possible.";
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
    void should_be_returned_actual_game_when_target_position_is_not_occupied_for_a_piece_of_same_color()
            throws PieceMovementException {

        Game game = gameManagement.init();
        gameManagement.playerMove(game, D7, D6);
        gameManagement.playerMove(game, D6, D5);
        gameManagement.playerMove(game, D8, D6);

        Assertions.assertInstanceOf(Game.class, game);

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
    void queen_right_horizontal_movement_should_not_permitted_when_an_other_piece_is_present_between_actual_and_target_position()
            throws PieceMovementException {

        Game game = gameManagement.init();
        gameManagement.playerMove(game, D2, D3);
        gameManagement.playerMove(game, E2, E3);
        gameManagement.playerMove(game, G2, G3);
        gameManagement.playerMove(game, D1, D2);

        assertEquals(WHITE_QUEEN, game.getChessboardPieceMap().get(D2));
        assertEquals(WHITE_PAWN, game.getChessboardPieceMap().get(B2));
        PieceMovementException thrown = Assertions.assertThrows(PieceMovementException.class, () ->
                gameManagement.playerMove(game, D2, H2));
        assertEquals(MOVEMENT_NOT_ALLOWED, thrown.getMessage());

    }

    @Test
    void queen_left_horizontal_movement_should_not_permitted_when_an_other_piece_is_present_between_actual_and_target_position()
            throws PieceMovementException {

        Game game = gameManagement.init();
        gameManagement.playerMove(game, D2, D3);
        gameManagement.playerMove(game, C2, C3);
        gameManagement.playerMove(game, A2, A3);
        gameManagement.playerMove(game, D1, D2);

        assertEquals(WHITE_QUEEN, game.getChessboardPieceMap().get(D2));
        assertEquals(WHITE_PAWN, game.getChessboardPieceMap().get(B2));
        PieceMovementException thrown = Assertions.assertThrows(PieceMovementException.class, () ->
                gameManagement.playerMove(game, D2, A2));
        assertEquals(MOVEMENT_NOT_ALLOWED, thrown.getMessage());

    }

    @Test
    void rook_horizontal_movement_should_be_permitted_when_no_other_pieces_are_present_between_actual_and_target_postion() throws PieceMovementException {

        Game game = gameManagement.init();
        gameManagement.playerMove(game, A2, A3);
        gameManagement.playerMove(game, A3, A4);
        gameManagement.playerMove(game, A1, A3);
        assertEquals(WHITE_ROOK, game.getChessboardPieceMap().get(A3));

        gameManagement.playerMove(game, A3, H3);

        assertEquals(WHITE_ROOK, game.getChessboardPieceMap().get(H3));

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

    @Test
    void white_queen_on_diagonal_from_up_to_down_movement_should_not_permitted_when_an_other_piece_is_present_between_actual_and_target_position() {

        Game game = gameManagement.init();
        assertEquals(WHITE_PAWN, game.getChessboardPieceMap().get(C2));
        assertEquals(WHITE_QUEEN, game.getChessboardPieceMap().get(D1));

        PieceMovementException thrown = Assertions.assertThrows(PieceMovementException.class, () ->
                gameManagement.playerMove(game, D1, A4));

        assertEquals(MOVEMENT_NOT_ALLOWED, thrown.getMessage());

    }

    @Test
    void black_queen_on_diagonal_from_up_to_down_movement_should_not_permitted_when_an_other_piece_is_present_between_actual_and_target_position() {

        Game game = gameManagement.init();
        assertEquals(BLACK_QUEEN, game.getChessboardPieceMap().get(D8));
        assertEquals(BLACK_PAWN, game.getChessboardPieceMap().get(E7));

        PieceMovementException thrown = Assertions.assertThrows(PieceMovementException.class, () ->
                gameManagement.playerMove(game, D8, G5));

        assertEquals(MOVEMENT_NOT_ALLOWED, thrown.getMessage());

    }

    @Test
    void black_queen_on_diagonal_from_up_to_down_movement_should_permitted_when_no_other_piece_is_present_between_actual_and_target_position() throws PieceMovementException {

        Game game = gameManagement.init();
        assertEquals(BLACK_PAWN, game.getChessboardPieceMap().get(E7));
        gameManagement.playerMove(game, E7, E6);
        assertEquals(BLACK_PAWN, game.getChessboardPieceMap().get(E6));
        assertEquals(BLACK_QUEEN, game.getChessboardPieceMap().get(D8));

        gameManagement.playerMove(game, D8, G5);

        assertEquals(BLACK_QUEEN, game.getChessboardPieceMap().get(G5));

    }

    @Test
    void queen_on_diagonal_from_up_to_down_movement_should_permitted_when_there_are_not_an_other_piece_is_between_actual_and_target_position() throws PieceMovementException {

        Game game = gameManagement.init();
        gameManagement.playerMove(game, C2, C3);

        assertEquals(WHITE_PAWN, game.getChessboardPieceMap().get(C3));
        assertEquals(EMPTY, game.getChessboardPieceMap().get(C2));
        assertEquals(WHITE_QUEEN, game.getChessboardPieceMap().get(D1));

        gameManagement.playerMove(game, D1, A4);
        assertEquals(EMPTY, game.getChessboardPieceMap().get(D1));
        assertEquals(WHITE_QUEEN, game.getChessboardPieceMap().get(A4));

        gameManagement.playerMove(game, A4, D1);
        assertEquals(EMPTY, game.getChessboardPieceMap().get(A4));
        assertEquals(WHITE_QUEEN, game.getChessboardPieceMap().get(D1));

    }

    @Test
    void queen_on_diagonal_from_up_to_down_movement_should_permitted_when_no_other_piece_is_present_between_actual_and_target_position()
            throws PieceMovementException {

        Game game = gameManagement.init();

        gameManagement.playerMove(game, C2, C3);
        assertEquals(EMPTY, game.getChessboardPieceMap().get(C2));
        assertEquals(WHITE_QUEEN, game.getChessboardPieceMap().get(D1));

        gameManagement.playerMove(game, D1, A4);

        assertEquals(WHITE_QUEEN, game.getChessboardPieceMap().get(A4));

    }

    @Test
    void white_queen_from_down_to_up_movement_should_not_permitted_when_an_other_piece_is_present_between_actual_and_target_position() {

        Game game = gameManagement.init();

        assertEquals(WHITE_PAWN, game.getChessboardPieceMap().get(E2));
        assertEquals(WHITE_QUEEN, game.getChessboardPieceMap().get(D1));

        PieceMovementException thrown = Assertions.assertThrows(PieceMovementException.class, () ->
                gameManagement.playerMove(game, D1, H5));

        assertEquals(MOVEMENT_NOT_ALLOWED, thrown.getMessage());

    }

    @Test
    void white_queen_from_down_to_up_movement_should_permitted_when_no_other_piece_are_present_between_actual_and_target_position() throws PieceMovementException {

        Game game = gameManagement.init();

        assertEquals(WHITE_PAWN, game.getChessboardPieceMap().get(E2));
        gameManagement.playerMove(game, E2, E3);
        assertEquals(WHITE_PAWN, game.getChessboardPieceMap().get(E3));
        assertEquals(WHITE_QUEEN, game.getChessboardPieceMap().get(D1));

        gameManagement.playerMove(game, D1, H5);

        assertEquals(WHITE_QUEEN, game.getChessboardPieceMap().get(H5));

    }

    @Test
    void black_queen_from_down_to_up_movement_should_not_permitted_when_an_other_piece_is_present_between_actual_and_target_position() {
        Game game = gameManagement.init();

        assertEquals(BLACK_PAWN, game.getChessboardPieceMap().get(E7));
        assertEquals(BLACK_QUEEN, game.getChessboardPieceMap().get(D8));

        PieceMovementException thrown = Assertions.assertThrows(PieceMovementException.class, () ->
                gameManagement.playerMove(game, D8, H4));

        assertEquals(MOVEMENT_NOT_ALLOWED, thrown.getMessage());

    }

    @Test
    void black_queen_from_down_to_up_movement_should_permitted_when_no_other_piece_are_present_between_actual_and_target_position() throws PieceMovementException {

        Game game = gameManagement.init();

        assertEquals(BLACK_PAWN, game.getChessboardPieceMap().get(E7));
        gameManagement.playerMove(game, E7, E6);
        assertEquals(BLACK_PAWN, game.getChessboardPieceMap().get(E6));
        assertEquals(BLACK_QUEEN, game.getChessboardPieceMap().get(D8));

        gameManagement.playerMove(game, D8, H4);

        assertEquals(BLACK_QUEEN, game.getChessboardPieceMap().get(H4));

    }

    @Test
    void knight_can_jump() throws PieceMovementException {
        Game game = gameManagement.init();
        assertEquals(WHITE_KNIGHT, game.getChessboardPieceMap().get(B1));
        assertEquals(WHITE_PAWN, game.getChessboardPieceMap().get(B2));
        assertEquals(WHITE_BISHOP, game.getChessboardPieceMap().get(C1));
        assertEquals(WHITE_PAWN, game.getChessboardPieceMap().get(C2));

        gameManagement.playerMove(game, B1, C3);

        assertEquals(WHITE_KNIGHT, game.getChessboardPieceMap().get(C3));


    }


}