package com.angelolagreca.chess.domain;

import com.angelolagreca.chess.domain.exception.IllegalPositionException;
import com.angelolagreca.chess.domain.exception.InitializationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ChessBoardTest {

    Chessboard chessboard = new Chessboard();

    public ChessBoardTest() throws InitializationException {
    }

    @Test(expected = IllegalPositionException.class)
    public void isMovimentIntoChessBoard_should_throw_a_illegalMovimentException_when_position_is_out_of_chessboard()
            throws InitializationException, IllegalPositionException {
        //Arrange
        Position positon = new Position('I', 1);
        //act
        chessboard.isAChessBoard(positon);

    }

    @Test
    public void isMovimentIntoChessBoard_should_throw_a_illegalMovimentException_message_when_position_x_is_out_of_chessboard()
            throws InitializationException, IllegalPositionException {
        //Arrange
        Position positon = new Position('I', 1);
        String expected = "Whit this moviment you are out of chessBoard.";
        //act
        try {
            chessboard.isAChessBoard(positon);
        } catch (IllegalPositionException exception) {
            assertEquals(expected, exception.getMessage());
        }

    }

    @Test
    public void isMovimentIntoChessBoard_should_throw_a_illegalMovimentException_message_when_position_y_is_out_of_chessboard()
            throws InitializationException, IllegalPositionException {
        //Arrange
        Position positon = new Position('H', 9);
        String expected = "Whit this moviment you are out of chessBoard.";
        //act
        try {
            chessboard.isAChessBoard(positon);
        } catch (IllegalPositionException exception) {
            assertEquals(expected, exception.getMessage());
        }

    }


}
