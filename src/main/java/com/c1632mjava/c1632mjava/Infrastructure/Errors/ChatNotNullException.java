package com.c1632mjava.c1632mjava.Infrastructure.Errors;

public class ChatNotNullException extends RuntimeException{
    public static final String CHAT_NOT_NULL_TEXT = "El chat no puede ser nulo.";

    public ChatNotNullException() {
        super(CHAT_NOT_NULL_TEXT);
    }
}
