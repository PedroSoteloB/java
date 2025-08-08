package com.pedro.borrowings.infraestructure.out.db.r2dbc;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("borrowings")
public record BorrowingEntity(
        @Id
        Long id,
        Long userId,
        Long bookId,
        Boolean returned,
        LocalDateTime borrowedAt,
        LocalDateTime dueDate,
        LocalDateTime returnedAt

) {}
