package com.bits.hyderabad.SocialMedia.exception;

import com.bits.hyderabad.SocialMedia.model.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class SocialMediaExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ErrorResponse getResponseStatusException(ResponseStatusException exception) {
        exception.getStackTrace();
        return new ErrorResponse(exception.getReason());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse getBadCredentialsException(RuntimeException runtimeException) {
        return new ErrorResponse(runtimeException.getMessage());
    }
}
