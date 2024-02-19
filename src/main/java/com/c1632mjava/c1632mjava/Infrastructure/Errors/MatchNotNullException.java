package com.c1632mjava.c1632mjava.Infrastructure.Errors;

public class MatchNotNullException extends RuntimeException{
    static String MATCH_CANT_BE_NULL = "Match no puede ser nulo!";

    public MatchNotNullException() {
        super(MATCH_CANT_BE_NULL);
    }
}
