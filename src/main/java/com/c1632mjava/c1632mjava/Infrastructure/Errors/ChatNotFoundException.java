package com.c1632mjava.c1632mjava.Infrastructure.Errors;

public class ChatNotFoundException extends RuntimeException{
    public static final String CHAT_NOT_EXISTS_BY_ID_TEXT = "No existe chat con el ID: ";

    public ChatNotFoundException(Long id) {
        super(CHAT_NOT_EXISTS_BY_ID_TEXT + id);
    }
}
