package com.angelolagreca.chess.domain.exception;

public class UnexpectedError extends RuntimeException{
    public UnexpectedError(String message) {
        super(message);
    }
}
