package com.angelolagreca.chess.application;

import com.angelolagreca.chess.domain.*;
import com.angelolagreca.chess.domain.exception.*;
import com.angelolagreca.chess.domain.vo.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import static com.angelolagreca.chess.domain.Chessboard.*;
import static com.angelolagreca.chess.domain.piece.TypeOfPiece.*;
import static com.angelolagreca.chess.domain.vo.Color.BLACK;
import static com.angelolagreca.chess.domain.vo.Color.WHITE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = GameManagementImpl.class)
class GameManagementImplTest {

    public static final String MOVEMENT_NOT_ALLOWED = "Movement is not possible.";
    private static final String EXPECTED_MESSAGE_FOR_BLACK_NO_TURN = "Is not turn of BLACK player";
    private static final String EXPECTED_MESSAGE_FOR_WHITE_NO_TURN = "Is not turn of WHITE player";
    @Autowired
    GameManagementImpl gameManagement;

    @Test
    void a_PieceMovementException_should_be_throw_when_a_black_piece_makes_the_first_move() {

        Game game = gameManagement.init();
        PieceMovementException thrown = Assertions.assertThrows(PieceMovementException.class, () ->
                gameManagement.playerMove(game, A7, A6)
        );

        assertEquals(EXPECTED_MESSAGE_FOR_BLACK_NO_TURN, thrown.getMessage());

    }

    @Test
    void a_PieceMovementException_should_be_throw_when_a_white_piece_tries_to_move_twice_consecutivel() throws PieceMovementException {

        Game game = gameManagement.init();
        gameManagement.playerMove(game, A2, A3);
        PieceMovementException thrown = Assertions.assertThrows(PieceMovementException.class, () ->
                gameManagement.playerMove(game, B2, B3)
        );

        assertEquals(EXPECTED_MESSAGE_FOR_WHITE_NO_TURN, thrown.getMessage());

    }

    @Test
    void chessboard_should_have_initial_configuration_of_piece_when_a_game_is_initialised() {
        Game game = gameManagement.init();

        assertEquals(WHITE_KING, game.getChessboardPieceMap().get(E1));
        assertEquals(WHITE_ROOK, game.getChessboardPieceMap().get(A1));
        assertEquals(BLACK_KNIGHT, game.getChessboardPieceMap().get(B8));
        assertEquals(BLACK_PAWN, game.getChessboardPieceMap().get(D7));
    }

    @Test
    void should_be_returned_actual_game_when_target_position_is_not_occupied_for_a_piece_of_same_color()
            throws PieceMovementException {

        Game game = gameManagement.init();
        makeAMovementToEnsureTheRotationOfThePlayersColor(WHITE, game);
        gameManagement.playerMove(game, D7, D6);
        makeAMovementToEnsureTheRotationOfThePlayersColor(WHITE, game);
        gameManagement.playerMove(game, D6, D5);
        makeAMovementToEnsureTheRotationOfThePlayersColor(WHITE, game);

        gameManagement.playerMove(game, D8, D6);

        Assertions.assertInstanceOf(Game.class, game);

    }

    @Test
    void a_PieceMovementException_should_be_throw_when_target_position_is_occupied_for_a_piece_of_same_color_()
            throws PieceMovementException {

        Game game = gameManagement.init();
        makeAMovementToEnsureTheRotationOfThePlayersColor(WHITE, game);

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
        makeAMovementToEnsureTheRotationOfThePlayersColor(Color.BLACK, game);
        gameManagement.playerMove(game, E2, E3);
        makeAMovementToEnsureTheRotationOfThePlayersColor(Color.BLACK, game);
        gameManagement.playerMove(game, G2, G3);
        makeAMovementToEnsureTheRotationOfThePlayersColor(Color.BLACK, game);
        gameManagement.playerMove(game, D1, D2);
        assertEquals(WHITE_QUEEN, game.getChessboardPieceMap().get(D2));
        assertEquals(WHITE_PAWN, game.getChessboardPieceMap().get(B2));
        makeAMovementToEnsureTheRotationOfThePlayersColor(Color.BLACK, game);

        PieceMovementException thrown = Assertions.assertThrows(PieceMovementException.class, () ->
                gameManagement.playerMove(game, D2, H2));
        assertEquals(MOVEMENT_NOT_ALLOWED, thrown.getMessage());

    }


    @Test
    void queen_left_horizontal_movement_should_not_permitted_when_an_other_piece_is_present_between_actual_and_target_position()
            throws PieceMovementException {

        Game game = gameManagement.init();
        gameManagement.playerMove(game, D2, D3);
        makeAMovementToEnsureTheRotationOfThePlayersColor(Color.BLACK, game);
        gameManagement.playerMove(game, C2, C3);
        makeAMovementToEnsureTheRotationOfThePlayersColor(Color.BLACK, game);
        gameManagement.playerMove(game, A2, A3);
        makeAMovementToEnsureTheRotationOfThePlayersColor(Color.BLACK, game);
        gameManagement.playerMove(game, D1, D2);
        assertEquals(WHITE_QUEEN, game.getChessboardPieceMap().get(D2));
        assertEquals(WHITE_PAWN, game.getChessboardPieceMap().get(B2));
        makeAMovementToEnsureTheRotationOfThePlayersColor(Color.BLACK, game);

        PieceMovementException thrown = Assertions.assertThrows(PieceMovementException.class, () ->
                gameManagement.playerMove(game, D2, A2));
        assertEquals(MOVEMENT_NOT_ALLOWED, thrown.getMessage());

    }

    @Test
    void rook_horizontal_movement_should_be_permitted_when_no_other_pieces_are_present_between_actual_and_target_postion() throws PieceMovementException {

        Game game = gameManagement.init();
        gameManagement.playerMove(game, A2, A3);
        makeAMovementToEnsureTheRotationOfThePlayersColor(Color.BLACK, game);
        gameManagement.playerMove(game, A3, A4);
        makeAMovementToEnsureTheRotationOfThePlayersColor(Color.BLACK, game);
        gameManagement.playerMove(game, A1, A3);
        assertEquals(WHITE_ROOK, game.getChessboardPieceMap().get(A3));
        makeAMovementToEnsureTheRotationOfThePlayersColor(Color.BLACK, game);

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
    void queen_down_vertically_movement_should_not_permitted_when_an_other_piece_is_present_between_actual_and_target_position() throws PieceMovementException {

        Game game = gameManagement.init();
        makeAMovementToEnsureTheRotationOfThePlayersColor(WHITE, game);
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
    void black_queen_on_diagonal_from_up_to_down_movement_should_not_permitted_when_an_other_piece_is_present_between_actual_and_target_position() throws PieceMovementException {

        Game game = gameManagement.init();
        assertEquals(BLACK_QUEEN, game.getChessboardPieceMap().get(D8));
        assertEquals(BLACK_PAWN, game.getChessboardPieceMap().get(E7));
        makeAMovementToEnsureTheRotationOfThePlayersColor(WHITE, game);

        PieceMovementException thrown = Assertions.assertThrows(PieceMovementException.class, () ->
                gameManagement.playerMove(game, D8, G5));

        assertEquals(MOVEMENT_NOT_ALLOWED, thrown.getMessage());

    }

    @Test
    void black_queen_on_diagonal_from_up_to_down_movement_should_permitted_when_no_other_piece_is_present_between_actual_and_target_position() throws PieceMovementException {

        Game game = gameManagement.init();
        makeAMovementToEnsureTheRotationOfThePlayersColor(WHITE, game);
        assertEquals(BLACK_PAWN, game.getChessboardPieceMap().get(E7));
        gameManagement.playerMove(game, E7, E6);
        assertEquals(BLACK_PAWN, game.getChessboardPieceMap().get(E6));
        assertEquals(BLACK_QUEEN, game.getChessboardPieceMap().get(D8));
        makeAMovementToEnsureTheRotationOfThePlayersColor(WHITE, game);
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
        makeAMovementToEnsureTheRotationOfThePlayersColor(Color.BLACK, game);
        gameManagement.playerMove(game, D1, A4);
        assertEquals(EMPTY, game.getChessboardPieceMap().get(D1));
        assertEquals(WHITE_QUEEN, game.getChessboardPieceMap().get(A4));
        makeAMovementToEnsureTheRotationOfThePlayersColor(Color.BLACK, game);

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
        makeAMovementToEnsureTheRotationOfThePlayersColor(Color.BLACK, game);

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
        makeAMovementToEnsureTheRotationOfThePlayersColor(Color.BLACK, game);

        gameManagement.playerMove(game, D1, H5);

        assertEquals(WHITE_QUEEN, game.getChessboardPieceMap().get(H5));

    }

    @Test
    void black_queen_from_down_to_up_movement_should_not_permitted_when_an_other_piece_is_present_between_actual_and_target_position() throws PieceMovementException {
        Game game = gameManagement.init();
        makeAMovementToEnsureTheRotationOfThePlayersColor(WHITE, game);
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
        moveWhiteKnightFromB1ToA3JustForGetGoodTournament(game);
        gameManagement.playerMove(game, E7, E6);
        assertEquals(BLACK_PAWN, game.getChessboardPieceMap().get(E6));
        assertEquals(BLACK_QUEEN, game.getChessboardPieceMap().get(D8));
        makeAMovementToEnsureTheRotationOfThePlayersColor(WHITE, game);

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

    @Test
    void register_of_capture_should_be_empty_when_no_piece_has_been_captured() throws PieceMovementException {

        Game game = gameManagement.init();
        makeAMovementToEnsureTheRotationOfThePlayersColor(WHITE, game);
        makeAMovementToEnsureTheRotationOfThePlayersColor(BLACK, game);

        assertTrue(game.getBlackPlayerCaptureRegister().isEmpty());
        assertTrue(game.getWhitePlayerCaptureRegister().isEmpty());


    }

    @Test
    void in_register_of_white_player_capture_should_be_a_black_white_knight_capture_it() throws PieceMovementException {

        Game game = gameManagement.init();
        gameManagement.playerMove(game, B1, C3);
        gameManagement.playerMove(game, B7, B5);

        gameManagement.playerMove(game, C3, B5);

        assertEquals(BLACK_PAWN, game.getWhitePlayerCaptureRegister().get(0));
        assertTrue(game.getBlackPlayerCaptureRegister().isEmpty());


    }

    private void makeAMovementToEnsureTheRotationOfThePlayersColor(Color color, Game game)
            throws PieceMovementException {
        if (WHITE.equals(color)) {
            if (!EMPTY.equals(game.getChessboardPieceMap().get(B1))) {
                moveWhiteKnightFromB1ToA3JustForGetGoodTournament(game);
            } else {
                returnWhiteKnightFromA3ToB1JustForGetGoodTournament(game);
            }
        } else if (Color.BLACK.equals(color)) {
            if (!EMPTY.equals(game.getChessboardPieceMap().get(B8))) {
                moveBlackKnightFromB8ToA6JustForGetGoodTournament(game);
            } else {
                returnBlackKnightFromA3ToB1JustForGetGoodTournament(game);
            }
        } else {
            throw new PieceMovementException("This configuration is not good for this method of tournament");
        }
    }

    private void moveWhiteKnightFromB1ToA3JustForGetGoodTournament(Game game) throws PieceMovementException {
        gameManagement.playerMove(game, B1, A3);
    }

    private void returnWhiteKnightFromA3ToB1JustForGetGoodTournament(Game game) throws PieceMovementException {
        gameManagement.playerMove(game, A3, B1);
    }

    private void moveBlackKnightFromB8ToA6JustForGetGoodTournament(Game game) throws PieceMovementException {
        gameManagement.playerMove(game, B8, A6);
    }

    private void returnBlackKnightFromA3ToB1JustForGetGoodTournament(Game game) throws PieceMovementException {
        gameManagement.playerMove(game, A6, B8);
    }


}