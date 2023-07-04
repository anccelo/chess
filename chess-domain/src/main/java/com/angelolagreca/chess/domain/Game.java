package com.angelolagreca.chess.domain;

import com.angelolagreca.chess.domain.piece.TypeOfPiece;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import static com.angelolagreca.chess.domain.piece.Color.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    String id = UUID.randomUUID().toString();

    private Player playerOne = new Player(WHITE);
    private Player playerTwo = new Player(BLACK);

    private Map<Chessboard, TypeOfPiece > chessboardPieceMap = new LinkedHashMap<>();
//    private Map<Integer, Map<ChessboardPosition, TypeOfPiece >> storicoGameMap;


}
