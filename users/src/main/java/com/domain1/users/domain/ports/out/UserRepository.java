package com.domain1.users.domain.ports.out;

import com.domain1.users.domain.model.User;

import reactor.core.publisher.Mono;

public interface UserRepository {
   Mono <User> getById(Long Id);
   
   Mono<Void> save (User user);
   
}