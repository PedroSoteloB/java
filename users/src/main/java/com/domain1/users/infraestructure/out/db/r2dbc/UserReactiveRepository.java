package com.domain1.users.infraestructure.out.db.r2dbc;

import org.springframework.stereotype.Repository;

import com.domain1.users.domain.model.User;
import com.domain1.users.domain.ports.out.UserRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;


@Repository
@AllArgsConstructor
public class UserReactiveRepository implements UserRepository {

   
    private UserR2dbcRepository userR2dbcRepository;
    private UserEntityMapper userEntityMapper;



    @Override
    public Mono<User> getById(Long id) {
        return userR2dbcRepository
                .findById(id)
                .map(userEntityMapper::userEntityToUser);
    }


    @Override
    public Mono<Void> save(User user) {
        return userR2dbcRepository
                .save(userEntityMapper.userToUserEntity(user))
                .then();
    }

}
