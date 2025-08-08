package com.domain1.users.aplication;

import com.domain1.users.domain.model.User;
import com.domain1.users.domain.ports.in.SaveUserUseCase;
import com.domain1.users.domain.ports.out.UserRepository;

import reactor.core.publisher.Mono;

public record SaveUserUseCaseImpl(UserRepository userRepository) implements SaveUserUseCase {

    @Override
    public Mono<Void> save(User user) {
        return userRepository.save(user);
    }
}
