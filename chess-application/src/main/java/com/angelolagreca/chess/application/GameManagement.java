package com.angelolagreca.chess.application;

import com.angelolagreca.chess.domain.*;

public interface GameManagement {

    Game init() throws Exception;

    Game playerMove(Game game, ChessboardPosition oldPosition, ChessboardPosition newPosition) throws Exception;


}
