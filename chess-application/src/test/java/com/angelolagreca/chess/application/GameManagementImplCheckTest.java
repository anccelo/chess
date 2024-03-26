package com.angelolagreca.chess.application;

import com.angelolagreca.chess.domain.Game;
import com.angelolagreca.chess.domain.exception.PieceMovementException;
import com.angelolagreca.chess.domain.vo.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.angelolagreca.chess.domain.Chessboard.*;
import static com.angelolagreca.chess.domain.piece.TypeOfPiece.*;
import static com.angelolagreca.chess.domain.vo.Color.WHITE;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = GameManagementImpl.class)
class GameManagementImplCheckTest {

    public static final String KING_IN_CHECK_WITH_THIS_MOVE = "King in check with this move";

    @Autowired
    GameManagementImpl gameManagement;

    @Test
    void should_return_exception_if_white_pawn_try_leave_white_king_in_check() throws PieceMovementException {

        Game game = gameManagement.init();
        gameManagement.playerMove(game, E2, E4);
        gameManagement.playerMove(game, E7, E5);
        gameManagement.playerMove(game, D2, D4);
        gameManagement.playerMove(game, D8, H4);

        PieceMovementException thrown = Assertions.assertThrows(PieceMovementException.class, () ->
                gameManagement.playerMove(game, F2, F3));

        assertEquals(KING_IN_CHECK_WITH_THIS_MOVE, thrown.getMessage());

    }
    @Test
    void should_return_exception_if_black_pawn_try_leave_black_king_in_check() throws PieceMovementException {

        Game game = gameManagement.init();
        gameManagement.playerMove(game, E2, E4);
        gameManagement.playerMove(game, E7, E5);
        gameManagement.playerMove(game, D1, H5);

        PieceMovementException thrown = Assertions.assertThrows(PieceMovementException.class, () ->
                gameManagement.playerMove(game, F7, F6));

        assertEquals(KING_IN_CHECK_WITH_THIS_MOVE, thrown.getMessage());

    }





}