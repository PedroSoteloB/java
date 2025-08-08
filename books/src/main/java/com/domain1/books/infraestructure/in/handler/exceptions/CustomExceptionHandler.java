package com.domain1.books.infraestructure.in.handler.exceptions;

import java.rmi.NotBoundException;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.domain1.books.exceptions.DuplicateIsbnException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public Map<String, String> NotFoundHandle(NotBoundException exception) {
        return Map.of("error", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> handleConstraintValidationErrors() {
        return Map.of("error", "custom validation");
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateIsbnException.class)
    public Map<String, String> handleDuplicateIsbn(DuplicateIsbnException ex) {
        return Map.of("error", ex.getMessage());
    }
}