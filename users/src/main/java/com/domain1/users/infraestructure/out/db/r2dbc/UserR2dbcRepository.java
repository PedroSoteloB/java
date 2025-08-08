package com.domain1.users.infraestructure.out.db.r2dbc;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserR2dbcRepository extends ReactiveCrudRepository<UserEntity,Long>{

}
