package com.domain1.books.infraestructure.in.dtos;

import jakarta.validation.constraints.NotNull;

public record BookPatchDTO(
    @NotNull
    Boolean available
) {}
