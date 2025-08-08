package com.pedro.borrowings.repository.impl;

import org.springframework.stereotype.Repository;

import com.pedro.borrowings.proxy.users.api.UserApi;
import com.pedro.borrowings.repository.UserRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserApi userApi;

    @Override
    public Mono<Boolean> existsById(Long userId) {
        return userApi.getById(userId)
                .map(user -> true)
                .defaultIfEmpty(false);
    }
}

