package com.test.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author Nikesh Maharjan
 *         nikesh.maharjan@f1soft.com
 */
@Getter
@Setter
public class DataDuplicationException extends RuntimeException {

    private ErrorResponse errorResponse;

    public DataDuplicationException(String message, String developerMessage) {
        super(message);
        errorResponse = new ErrorResponse();

        errorResponse.setDeveloperMsg(developerMessage);
        errorResponse.setErrorMsg(message);
        errorResponse.setResponseCode(HttpStatus.CONFLICT.value());
        errorResponse.setResponseStatus(HttpStatus.CONFLICT);
    }
}
