package com.pedro.borrowings.proxy.users.api.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.pedro.borrowings.proxy.users.api.UserApi;
import com.pedro.borrowings.proxy.users.dtos.UserDto;

import reactor.core.publisher.Mono;

@Component
public record UserApiImpl(
        @Qualifier("userClient") WebClient webClient) implements UserApi {

    @Override
    public Mono<UserDto> getById(Long userId) {
        return webClient
                .get()
                .uri("/users/{id}", userId)
                .retrieve()
                .bodyToMono(UserDto.class);
    }
}
