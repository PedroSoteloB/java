package com.domain1.users.infraestructure.in.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.domain1.users.domain.model.User;
import com.domain1.users.infraestructure.in.dtos.UserDTO;
import com.domain1.users.infraestructure.in.dtos.UserInDTO;


@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    UserDTO userToUserDto(User user);

    @Mapping(target = "id", ignore = true)
    User userInDtoTOUser(UserInDTO userInDTO);
}