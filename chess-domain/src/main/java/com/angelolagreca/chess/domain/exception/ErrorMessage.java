package com.angelolagreca.chess.domain.exception;

import com.fasterxml.jackson.annotation.*;

public class ErrorMessage {

    @JsonProperty
    private final String message;

    public ErrorMessage(String errorMessage) {
        this.message = errorMessage;
    }


}
