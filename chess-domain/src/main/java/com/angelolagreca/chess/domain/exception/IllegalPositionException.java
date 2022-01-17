package com.angelolagreca.chess.domain.exception;

public class IllegalPositionException extends Exception{
    public IllegalPositionException(String message) {
        super(message);
    }
}
