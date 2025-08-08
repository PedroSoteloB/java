package com.pedro.borrowings.services;

import com.pedro.borrowings.dtos.BorrowingDTO;
import com.pedro.borrowings.dtos.BorrowingInDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BorrowingService {
    Mono<Void> create(BorrowingInDTO borrowingInDTO);
    Flux<BorrowingDTO> getByUserId(Long userId);
    Mono<Void> returnBook(Long borrowingId);
    Flux<BorrowingDTO> getAll();
}
