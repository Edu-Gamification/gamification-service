package com.business.money.exception;

import com.business.money.exception.exceptions.EventTypeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springdoc.api.ErrorMessage;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.util.stream.Collectors;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ErrorMessage> handleNotValidException(MethodArgumentNotValidException exception) {
        var errorMessage = exception.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return ResponseEntity.status(exception.getStatusCode()).body( new ErrorMessage(errorMessage));
    }

    @ExceptionHandler(EventTypeNotFoundException.class)
    private ResponseEntity<ErrorMessage> handleEventTypeNotFound(EventTypeNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new ErrorMessage(exception.getMessage()));
    }

}
