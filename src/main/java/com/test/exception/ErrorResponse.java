package com.test.exception;

/**
 * Created by intern1 on 5/4/2017.
 */
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorResponse {

    private int responseCode;
    private HttpStatus responseStatus;
    private String errorMsg;
    private String developerMsg;

    ErrorResponse(){

    }

    public ErrorResponse(int responseCode, HttpStatus status, String errorMsg, String developerMsg) {
        this.responseCode = responseCode;
        this.responseStatus = status;
        this.errorMsg = errorMsg;
        this.developerMsg = developerMsg;
    }

}
