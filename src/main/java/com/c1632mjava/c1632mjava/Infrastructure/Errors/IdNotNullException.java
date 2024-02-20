package com.c1632mjava.c1632mjava.Infrastructure.Errors;

public class IdNotNullException extends RuntimeException{
    public static final String ID_NOT_NULL_PART_1_TEXT = "El ID del ";
    public static final String ID_NOT_NULL_PART_2_TEXT = " no puede ser nulo.";

    public IdNotNullException(String subject) {
        super(ID_NOT_NULL_PART_1_TEXT + subject + ID_NOT_NULL_PART_2_TEXT);
    }
}
