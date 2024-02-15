package com.c1632mjava.c1632mjava.Infrastructure.Errors;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
