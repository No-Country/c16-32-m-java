package com.c1632mjava.c1632mjava.Infrastructure.Errors;

public class MatchNotFoundException extends RuntimeException{
    static String MATCH_DOESNT_EXIST_BY_ID_TEXT = "No existe match con ese ID: ";

    public MatchNotFoundException(Long id) {
        super(MATCH_DOESNT_EXIST_BY_ID_TEXT + id);
    }
}
