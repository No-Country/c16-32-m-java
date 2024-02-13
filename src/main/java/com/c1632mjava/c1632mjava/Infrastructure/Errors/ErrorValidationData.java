package com.c1632mjava.c1632mjava.Infrastructure.Errors;
import org.springframework.validation.FieldError;

public record ErrorValidationData(String field, String error) {
    public ErrorValidationData(FieldError fieldError) {
        this(fieldError.getField(),
                fieldError.getDefaultMessage());
    }
}

