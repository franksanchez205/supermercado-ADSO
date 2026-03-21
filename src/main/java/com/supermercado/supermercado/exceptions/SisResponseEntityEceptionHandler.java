package com.supermercado.supermercado.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class SisResponseEntityEceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handlExceotion(Exception ex, WebRequest request) throws Exception {

        SisExceptionResponse sisExceptionResponse = new SisExceptionResponse(new Date(), ex.getMessage());

        return new ResponseEntity<>(sisExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handlNotFoundExceotion(Exception ex, WebRequest request) throws Exception {

        SisExceptionResponse sisExceptionResponse = new SisExceptionResponse(new Date(), ex.getMessage());

        return new ResponseEntity<>(sisExceptionResponse, HttpStatus.NOT_FOUND);

    }
}