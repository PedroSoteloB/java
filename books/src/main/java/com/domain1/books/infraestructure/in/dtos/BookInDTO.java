package com.domain1.books.infraestructure.in.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BookInDTO(
    @NotBlank
    @Size(min = 1, max = 100)
    String title,

    @NotBlank
    @Size(min = 1, max = 100)
    String author,

    @NotBlank
    @Size(min = 10, max = 13)
    String isbn,

    @NotNull
    Boolean available
) {}