package com.supermercado.supermercado.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String resource, String identifier) {
        this(String.format("%s con identificador %s no encontrado", resource, identifier));
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}