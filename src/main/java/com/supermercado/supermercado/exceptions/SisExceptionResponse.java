package com.supermercado.supermercado.exceptions;

import java.util.Date;

public class SisExceptionResponse {

    private String message;
    private Date timestamp;
    
    public SisExceptionResponse(Date timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }


        
}