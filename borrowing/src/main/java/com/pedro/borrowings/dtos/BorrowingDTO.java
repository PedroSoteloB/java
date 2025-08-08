package com.pedro.borrowings.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record BorrowingDTO(
        Long id,
        Long userId,
        Long bookId,
        Boolean returned,
        LocalDateTime borrowedAt,
        LocalDateTime dueDate,
        LocalDateTime returnedAt
) {}