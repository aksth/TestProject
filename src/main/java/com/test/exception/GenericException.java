package com.test.exception;

import org.springframework.http.HttpStatus;

public class GenericException extends RuntimeException {

    private ErrorResponse errorResponse;

    public GenericException(ErrorResponse errorResponse) {
        super(errorResponse.getErrorMsg());
        this.errorResponse = errorResponse;
    }
    
    public GenericException(int errorCode, HttpStatus status, String message, String developerMsg){
        super(message);
        this.errorResponse = new ErrorResponse(errorCode, status, message, developerMsg);
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

}
