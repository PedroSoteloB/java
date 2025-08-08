package com.domain1.users.infraestructure.out.db.r2dbc;

import org.mapstruct.Mapper;

import com.domain1.users.domain.model.User;

@Mapper (componentModel = "spring")
public interface UserEntityMapper {

    User userEntityToUser(UserEntity userEntity);
    UserEntity userToUserEntity(User user);
}
