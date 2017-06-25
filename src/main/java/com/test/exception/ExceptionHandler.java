package com.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {GenericException.class})
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        GenericException profileException = (GenericException) ex;
        ErrorResponse errorResponse = profileException.getErrorResponse();

        return new ResponseEntity<>(errorResponse, errorResponse.getResponseStatus());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DataDuplicationException.class)
    public ResponseEntity<ErrorResponse> handleDataDuplicationException(DataDuplicationException e) {
        return new ResponseEntity<>(e.getErrorResponse(), HttpStatus.CONFLICT);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnautorizedException(UnauthorizedException e) {
        return new ResponseEntity<>(e.getErrorResponse(), HttpStatus.UNAUTHORIZED);
    }
}