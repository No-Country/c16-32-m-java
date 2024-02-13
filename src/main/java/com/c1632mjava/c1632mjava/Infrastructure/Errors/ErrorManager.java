package com.c1632mjava.c1632mjava.Infrastructure.Errors;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorManager {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity treatError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity treatError400(MethodArgumentNotValidException e) {
        var errors = e.getFieldErrors().stream().map(ErrorValidationData::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(IntegrityValidation.class)
    public ResponseEntity BusinessRulesError(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity ValidationException(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
