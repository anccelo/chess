package com.angelolagreca.chess.domain;

import com.angelolagreca.chess.domain.piece.TypeOfPiece;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.angelolagreca.chess.domain.piece.Color.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Game {


    private Player playerOne = new Player(WHITE);
    private Player playerTwo = new Player(BLACK);

    private Map<ChessboardPosition, TypeOfPiece > chessboardPieceMap = new LinkedHashMap<>();
    private Map<Integer, Map<ChessboardPosition, TypeOfPiece >> storicoGameMap;



}
