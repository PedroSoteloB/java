package com.pedro.borrowings.model.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Borrowing {
    private Long id;
    private Long userId;
    private Long bookId;
    private Boolean returned;
    private LocalDateTime borrowedAt; 
    private LocalDateTime dueDate;   
    private LocalDateTime returnedAt;
}