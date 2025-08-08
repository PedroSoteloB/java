package com.pedro.borrowings.infraestructure.handler.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public Mono<String> handleNotFound(NotFoundException ex) {
        return Mono.just(ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public Mono<String> handleBadRequest(BadRequestException ex) {
        return Mono.just(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Mono<String> handleGeneric(Exception ex) {
        return Mono.just("Error interno: " + ex.getMessage());
    }
}
