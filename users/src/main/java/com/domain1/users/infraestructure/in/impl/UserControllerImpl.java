package com.domain1.users.infraestructure.in.impl;

import org.springframework.web.bind.annotation.RestController;

import com.domain1.users.domain.model.User;
import com.domain1.users.domain.ports.in.GetUserUseCase;
import com.domain1.users.domain.ports.in.SaveUserUseCase;
import com.domain1.users.infraestructure.in.UserController;
import com.domain1.users.infraestructure.in.dtos.UserDTO;
import com.domain1.users.infraestructure.in.dtos.UserInDTO;
import com.domain1.users.infraestructure.in.mapper.UserDtoMapper;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class UserControllerImpl implements UserController {

    private final GetUserUseCase getUserUseCase;
    private final SaveUserUseCase saveUserUseCase;
   
    private final UserDtoMapper userMapper;

    @Override
    public Mono<UserDTO> getById(Long id) {
        return getUserUseCase
                .getById(id)
                .map(userMapper::userToUserDto);
    }

    @Override
    public Mono<Void> create(@Valid UserInDTO userInDTO) {
        User user = userMapper.userInDtoTOUser(userInDTO);
        return saveUserUseCase
                .save(user);
    }
    

}