package com.c1632mjava.c1632mjava.Infrastructure.Errors;

public class IdLessThanOneException extends RuntimeException{
    public IdLessThanOneException(String message) {
        super(message);
    }
}
