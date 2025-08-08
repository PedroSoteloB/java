package com.pedro.borrowings.services.impl;

import org.springframework.stereotype.Service;

import com.pedro.borrowings.infraestructure.handler.exceptions.NotFoundException;
import com.pedro.borrowings.proxy.users.api.UserApi;
import com.pedro.borrowings.services.UserService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserApi userApi;

    @Override
    public Mono<Void> validateUserExists(Long userId) {
        return userApi.getById(userId)
                .switchIfEmpty(Mono.error(new NotFoundException("Usuario no encontrado")))
                .then();
    }
}
