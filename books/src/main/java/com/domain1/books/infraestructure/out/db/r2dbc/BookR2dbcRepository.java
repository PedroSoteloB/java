package com.domain1.books.infraestructure.out.db.r2dbc;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface BookR2dbcRepository extends ReactiveCrudRepository<BookEntity, Long> {
    Mono<BookEntity> findByIsbn(String isbn);
}
