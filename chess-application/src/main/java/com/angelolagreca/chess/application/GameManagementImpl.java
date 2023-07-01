package com.angelolagreca.chess.application;

import com.angelolagreca.chess.domain.Game;
import com.angelolagreca.chess.domain.PositionEnum;
import com.angelolagreca.chess.domain.piece.PieceEnum;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.angelolagreca.chess.domain.piece.PieceEnum.BLACK_KING;
import static com.angelolagreca.chess.domain.piece.PieceEnum.WHITE_KING;

@Component
public class GameManagementImpl implements GameManagement {
    @Override
    public Game init() {

        Game game = new Game();

        Map<PositionEnum, PieceEnum> chessboardOfGame = game.getChessboard().getCheesboard();
        chessboardOfGame.put(PositionEnum.E1, WHITE_KING);
        chessboardOfGame.put(PositionEnum.E8, BLACK_KING);

        return game;
    }
}
