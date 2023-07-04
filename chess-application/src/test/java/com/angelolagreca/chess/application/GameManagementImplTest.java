package com.angelolagreca.chess.application;

import com.angelolagreca.chess.domain.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import static com.angelolagreca.chess.domain.Chessboard.E1;
import static com.angelolagreca.chess.domain.piece.TypeOfPiece.WHITE_KING;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = GameManagementImpl.class)
class GameManagementImplTest {

    @Autowired
    private GameManagementImpl gameManagement;

    @Test
    void test() {
        Game actual = gameManagement.init();

        assertEquals(actual.getChessboardPieceMap().get(E1), WHITE_KING);
    }

}