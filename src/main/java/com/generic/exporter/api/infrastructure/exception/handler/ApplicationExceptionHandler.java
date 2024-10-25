package com.generic.exporter.api.infrastructure.exception.handler;

import com.generic.exporter.api.infrastructure.exception.GenericsException;
import com.generic.exporter.api.infrastructure.exception.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(GenericsException.class)
    public ResponseEntity<StandardError> generics(GenericsException e, HttpServletRequest request) {
        String error = e.getClass().getName();
        HttpStatus status = e.getStatus();
        StandardError err = new StandardError(new Date(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> exception(Exception e, HttpServletRequest request) {
        String error = e.getClass().getName();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(new Date(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}