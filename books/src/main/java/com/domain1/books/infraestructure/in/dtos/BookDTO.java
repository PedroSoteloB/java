package com.domain1.books.infraestructure.in.dtos;

public record BookDTO(
    Long id,
    String title,
    String author,
    String isbn,
    Boolean available
) {
}
