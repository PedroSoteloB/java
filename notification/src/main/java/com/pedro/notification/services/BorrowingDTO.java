package com.pedro.notification.services;

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