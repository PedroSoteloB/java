package com.pedro.borrowings.repository;

import reactor.core.publisher.Mono;

public interface UserRepository {
    Mono<Boolean> existsById(Long userId);
}
