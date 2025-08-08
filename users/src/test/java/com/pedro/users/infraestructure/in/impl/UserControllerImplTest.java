package com.pedro.users.infraestructure.in.impl;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.domain1.users.domain.model.User;
import com.domain1.users.domain.ports.in.GetUserUseCase;
import com.domain1.users.domain.ports.in.SaveUserUseCase;
import com.domain1.users.infraestructure.in.dtos.UserDTO;
import com.domain1.users.infraestructure.in.dtos.UserInDTO;
import com.domain1.users.infraestructure.in.impl.UserControllerImpl;
import com.domain1.users.infraestructure.in.mapper.UserDtoMapper;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class UserControllerImplTest {

    @Mock
    private GetUserUseCase getUserUseCase;
    
    @Mock
    private SaveUserUseCase saveUserUseCase;
    
    @Mock
    private UserDtoMapper userDtoMapper;
    
    @InjectMocks
    private UserControllerImpl userControllerImpl;
    
    @Test
    @DisplayName("should return UserDTO when user exists")
    void shouldReturnUserDTOWhenUserExists() {

        Long userId = 1L;
        User user = new User(userId, "pedro", "sotelo", "psb@gmail.com", "9938484");
        UserDTO userDto = new UserDTO(userId, "pedro", "sotelo", "psb@gmail.com", "9938484");


        when(getUserUseCase.getById(userId)).thenReturn(Mono.just(user));
        when(userDtoMapper.userToUserDto(user)).thenReturn(userDto);

        
        StepVerifier.create(userControllerImpl.getById(userId))
                .expectNext(userDto)
                .verifyComplete();


        
    }

    @Test
    @DisplayName("should call SaveUserUseCase when creating a new user")
    void shouldCreateUserSuccessfully() {
    
    UserInDTO userInDTO = new UserInDTO("Pedro", "Sotelo", "psb@gmail.com", "9938484");
    User user = new User(null, "Pedro", "Sotelo", "psb@gmail.com", "9938484");

    when(userDtoMapper.userInDtoTOUser(userInDTO)).thenReturn(user);
    when(saveUserUseCase.save(user)).thenReturn(Mono.empty());

    
    StepVerifier.create(userControllerImpl.create(userInDTO))
            .verifyComplete();
}
}
