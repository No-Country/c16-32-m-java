package com.c1632mjava.c1632mjava.Infrastructure.Errors;

public class UserNotFoundException extends RuntimeException{
    public static String USER_NOT_EXISTS_BY_ID_TEXT = "No existe usuario con ese ID: ";
    public static String USER_NOT_EXISTS_BY_EMAIL_TEXT = "No existe usuario con ese email: ";

    public UserNotFoundException(Long id) {
        super(USER_NOT_EXISTS_BY_ID_TEXT + id);
    }
    public UserNotFoundException(String email) {
        super(USER_NOT_EXISTS_BY_EMAIL_TEXT + email);
    }
}
