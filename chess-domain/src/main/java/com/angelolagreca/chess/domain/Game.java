package com.angelolagreca.chess.domain;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    private Player white;
    private Player black;

    Chessboard chessboard = new Chessboard();


}
