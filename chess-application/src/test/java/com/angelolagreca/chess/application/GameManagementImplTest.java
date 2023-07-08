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
    void a_PieceMovementException_should_be_throw_when_target_position_is_occuped_for_a_piece_of_same_color() {

        Game game = gameManagement.init();

        PieceMovementException thrown = Assertions.assertThrows(PieceMovementException.class, () -> {
            gameManagement.playerMove(game, E8, E7);
        });

        Assertions.assertEquals(MOVEMENT_NOT_ALLOWED, thrown.getMessage());


    }

}