package com.generic.exporter.api.infrastructure.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GenericsException extends RuntimeException {
    private final HttpStatus status;

    public GenericsException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }

}
