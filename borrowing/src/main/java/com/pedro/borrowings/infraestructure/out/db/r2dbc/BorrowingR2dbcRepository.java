package com.pedro.borrowings.infraestructure.out.db.r2dbc;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;

public interface BorrowingR2dbcRepository extends ReactiveCrudRepository<BorrowingEntity, Long> {
    Flux<BorrowingEntity> findByUserId(Long userId);
}
