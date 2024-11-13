package com.andersen.hw.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler
    public ErrorResponse handleIllegalFlagException(IllegalFlagException e) {
        log.info("Activation of users is not permitted.");
        return new ErrorResponse(e.getClass().getSimpleName(),
                Arrays.stream(e.getStackTrace()).findFirst().toString(),
                e.getMessage(),
                LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleEntityNotFoundException(final EntityNotFoundException e) {
        log.error("Entity not founded.", e);
        return new ErrorResponse(e.getClass().getSimpleName(),
                Arrays.stream(e.getStackTrace()).findFirst().toString(),
                e.getMessage(),
                LocalDateTime.now());
    }
}
