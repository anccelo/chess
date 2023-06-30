package com.angelolagreca.chess.application;

import com.angelolagreca.chess.domain.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;

import static org.junit.jupiter.api.Assertions.*;

class GameManagementImplTest {

    @Autowired
    private GameManagementImpl gameManagement;

    @Test
    void test() {
        Game actual = gameManagement.init();

        Assertions.assertEquals(
                actual.getChessboard().getCheesboard().get(new Position('E',1)),
                new Position('E',1) );
    }

}