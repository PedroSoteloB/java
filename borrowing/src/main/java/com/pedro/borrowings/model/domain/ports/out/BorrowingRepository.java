package com.pedro.borrowings.model.domain.ports.out;


import com.pedro.borrowings.model.domain.Borrowing;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BorrowingRepository {
    Mono<Void> save(Borrowing borrowing);
    Mono<Void> update(Borrowing borrowing);
    Flux<Borrowing> findByUserId(Long userId);
    Mono<Borrowing> findById(Long id);
    Flux<Borrowing> findall();
}
