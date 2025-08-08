package com.pedro.borrowings.dtos;

import jakarta.validation.constraints.NotNull;

public record BorrowingPatchDTO(
        @NotNull Boolean returned
) {}
