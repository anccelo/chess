package com.angelolagreca.chess.application;

import com.angelolagreca.chess.domain.*;
import com.angelolagreca.chess.domain.exception.*;
import com.angelolagreca.chess.domain.vo.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import static com.angelolagreca.chess.domain.Chessboard.*;
import static com.angelolagreca.chess.domain.piece.TypeOfPiece.EMPTY;
import static com.angelolagreca.chess.domain.vo.Color.WHITE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = GameManagementImpl.class)
class GameManagementImplAlgebraicNotationTest {

    @Autowired
    GameManagement gameManagement;


    @Test
    void should_return_a3_when_first_move_is_white_pawn_from_a2_to_a3() throws PieceMovementException {

        Game game = gameManagement.init();
        gameManagement.playerMove(game, A2, A3);

        assertEquals("1. a3", game.printAlgebraicNotationRegister());

    }


    @Test
    void should_return_Nf3_when_first_move_is_white_knight_from_g1_to_f3() throws PieceMovementException {

        Game game = gameManagement.init();
        gameManagement.playerMove(game, G1, F3);

        assertEquals("1. Nf3", game.printAlgebraicNotationRegister());

    }

    @Test
    void should_return_a3_Nf3_when_move_a3_Na6_Nf3() throws PieceMovementException {

        Game game = gameManagement.init();
        gameManagement.playerMove(game, A2, A3);
        this.makeAMovementToEnsureTheRotationOfThePlayersColor(Color.BLACK, game);
        gameManagement.playerMove(game, G1, F3);

        assertEquals("1. a3 Na6 2. Nf3", game.printAlgebraicNotationRegister());
    }

    @Test
    void there_should_be_ax_when_a_pawn_move_capture_a_piece_from_a_line() throws PieceMovementException {
        Game game = gameManagement.init();
        gameManagement.playerMove(game, A2, A4);
        gameManagement.playerMove(game, B7, B5);

        gameManagement.playerMove(game, A4, B5);

        assertEquals("1. a4 b5 2. axb5", game.printAlgebraicNotationRegister());
    }

    @Test
    void there_should_be_cx_when_a_pawn_move_capture_c_piece_from_a_line() throws PieceMovementException {
        Game game = gameManagement.init();
        gameManagement.playerMove(game, C2, C4);
        gameManagement.playerMove(game, B7, B5);

        gameManagement.playerMove(game, C4, B5);

        assertEquals("1. c4 b5 2. cxb5", game.printAlgebraicNotationRegister());

    }

    @Test
    void there_should_be_an_x_when_a_knight_move_capture_a_piece() throws PieceMovementException {
        Game game = gameManagement.init();
        gameManagement.playerMove(game, B1, C3);
        gameManagement.playerMove(game, B7, B5);

        gameManagement.playerMove(game, C3, B5);

        assertEquals("1. Nc3 b5 2. Nxb5", game.printAlgebraicNotationRegister());

    }

    @Test
    void there_should_be_bxa4_when_a_black_pawn_capture_a_white_piece() throws PieceMovementException {
        Game game = gameManagement.init();
        gameManagement.playerMove(game, A2, A4);
        gameManagement.playerMove(game, B7, B5);
        gameManagement.playerMove(game, H2, H3);

        gameManagement.playerMove(game, B5, A4);

        assertEquals("1. a4 b5 2. h3 bxa4", game.printAlgebraicNotationRegister());

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