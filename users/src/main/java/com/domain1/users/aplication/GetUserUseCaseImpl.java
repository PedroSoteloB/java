package com.domain1.users.aplication;

import com.domain1.users.domain.model.User;
import com.domain1.users.domain.ports.in.GetUserUseCase;
import com.domain1.users.domain.ports.out.UserRepository;
import com.domain1.users.exceptions.UserNotFoundException;

import reactor.core.publisher.Mono;

public record GetUserUseCaseImpl (UserRepository userRepository)implements GetUserUseCase{ 
@Override
    public Mono<User> getById(Long id) {
        return userRepository
                .getById(id)
                .switchIfEmpty(Mono.error(new UserNotFoundException(id)));
    }
}
