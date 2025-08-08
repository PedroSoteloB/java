package com.pedro.borrowings.services;

import reactor.core.publisher.Mono;

public interface UserService {
    Mono<Void> validateUserExists(Long userId);
}
