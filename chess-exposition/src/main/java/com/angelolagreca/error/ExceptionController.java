package com.angelolagreca.error;

import com.angelolagreca.chess.domain.exception.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(PieceMovementException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ErrorMessage> handleException(Exception exception) {

        return new ResponseEntity<>(new ErrorMessage(exception.getMessage()), HttpStatus.NOT_ACCEPTABLE);
    }

}
