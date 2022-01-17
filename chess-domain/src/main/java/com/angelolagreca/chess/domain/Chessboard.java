package com.angelolagreca.chess.domain;


import com.angelolagreca.chess.domain.exception.IllegalPositionException;
import com.angelolagreca.chess.domain.exception.InitializationException;
import com.angelolagreca.chess.domain.piece.Color;
import com.angelolagreca.chess.domain.piece.King;
import com.angelolagreca.chess.domain.piece.Piece;
import lombok.Getter;

import java.util.*;

@Getter
public class Chessboard {

    private final Map<Position, Piece> cheesboard = new LinkedHashMap<>();

    public Chessboard() throws InitializationException {
        for(int i = 1 ; i < 9 ; i++ ){
            for( char c = 'A'; c< 'I'; c++ ){
                cheesboard.put(new Position(c, i), new King(Color.NO_COLOR));
            }
        }
    }

    public void isAChessBoard(Position position) throws IllegalPositionException {
        if(position.getX() <'A' || position.getX() > 'H'
                || position.getY() <1 ||position.getY()>8 )
            throw new IllegalPositionException("Whit this moviment you are out of chessBoard.");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<Position, Piece>> iter = cheesboard.entrySet().iterator();
        while (iter.hasNext()) {

            Map.Entry<Position, Piece> entry = iter.next();
            if(entry.getKey().getX() == 'H') {
                sb.append(entry.getKey());
                sb.append('=');
                sb.append(entry.getValue());
                sb.append("\n");
            }else {
                sb.append(entry.getKey());
                sb.append('=');
                sb.append(entry.getValue());
                sb.append(',').append(' ');
            }
        }
        return sb.toString();

    }


}
