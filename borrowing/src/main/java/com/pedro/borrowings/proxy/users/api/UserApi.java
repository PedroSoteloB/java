package com.pedro.borrowings.proxy.users.api;

import com.pedro.borrowings.proxy.users.dtos.UserDto;

import reactor.core.publisher.Mono;

public interface UserApi {
    Mono<UserDto> getById(Long userId);
}
