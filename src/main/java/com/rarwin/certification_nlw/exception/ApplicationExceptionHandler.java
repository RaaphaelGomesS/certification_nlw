package com.rarwin.certification_nlw.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(StudentException.class)
    public ResponseEntity handlerException(StudentException e) {
        log.info("error: " + e.getMessage());

        DefaultError error = new DefaultError(HttpStatus.BAD_REQUEST.value(), e.getMessage());

        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
}
