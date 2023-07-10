package com.angelolagreca.chess.domain;

import com.angelolagreca.chess.domain.vo.Color;
import com.angelolagreca.chess.domain.piece.TypeOfPiece;
import org.junit.jupiter.api.Test;

import static com.angelolagreca.chess.domain.Chessboard.*;
import static org.junit.jupiter.api.Assertions.*;

class MovementTest {
    Chessboard actualPosition;
    Chessboard targetPosition;
    private Movement movement;
    private final Game game = new Game();

    @Test
    void should_return_true_when_target_position_is_occupied_by_a_piece_of_the_same_color() {

        movement = new Movement(TypeOfPiece.BLACK_QUEEN, game);

        boolean actual = movement.isTargetPositionOccupiedByAPieceOfItsOwnColour(D1, D2);
        assertTrue(actual);

    }

    @Test
    void should_return_false_when_target_position_is_free() {
        actualPosition = D1;
        targetPosition = D3;
        movement = new Movement(TypeOfPiece.BLACK_QUEEN, game);

        Color targetPositionColor = game.getChessboardPieceMap().get(targetPosition).getColor();
        Color initialPositionColor = game.getChessboardPieceMap().get(actualPosition).getColor();

        boolean actual = movement.isTargetPositionOccupiedByAPieceOfItsOwnColour(D1, D3);
        assertNotEquals(initialPositionColor, targetPositionColor);
        assertFalse(actual);

    }

    @Test
    void should_return_false_when_target_position_is_occupied_by_a_piece_of_the_other_color() {
        movement = new Movement(TypeOfPiece.BLACK_QUEEN, game);

        actualPosition = D1;
        targetPosition = D7;

        Color targetPositionColor = game.getChessboardPieceMap().get(targetPosition).getColor();
        Color initialPositionColor = game.getChessboardPieceMap().get(actualPosition).getColor();

        boolean actual = movement.isTargetPositionOccupiedByAPieceOfItsOwnColour(actualPosition, targetPosition);
        assertNotEquals(initialPositionColor, targetPositionColor);
        assertFalse(actual);

    }


}