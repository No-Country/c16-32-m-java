package com.c1632mjava.c1632mjava.Infrastructure.Errors;

public class IdLessThanOneException extends RuntimeException{
    public static final String ID_LESS_THAN_ONE_PART_1_TEXT = "El ID del ";
    public static final String ID_LESS_THAN_ONE_PART_2_TEXT = " no puede ser menor a 1.";

    public IdLessThanOneException(String subject) {
        super(ID_LESS_THAN_ONE_PART_1_TEXT + subject + ID_LESS_THAN_ONE_PART_2_TEXT);
    }
}
