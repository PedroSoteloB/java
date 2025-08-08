package com.pedro.borrowings.dtos;

import jakarta.validation.constraints.NotNull;

public record BorrowingInDTO(
        @NotNull Long userId,
        @NotNull Long bookId
) {}
