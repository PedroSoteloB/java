package com.pedro.users.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.domain1.users.aplication.GetUserUseCaseImpl;
import com.domain1.users.domain.model.User;
import com.domain1.users.domain.ports.out.UserRepository;
import com.domain1.users.exceptions.UserNotFoundException;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class GetUserUseCaseImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetUserUseCaseImpl getUserUseCaseImpl;

    @Test
    @DisplayName("should return User when ID exists")
    void shouldReturnUserWhenExists() {
       
        Long userId = 1L;
        User user = new User(userId, "Pedro", "Sotelo", "pedro@gmail.com", "916256104");

        when(userRepository.getById(userId)).thenReturn(Mono.just(user));


        StepVerifier.create(getUserUseCaseImpl.getById(userId))
                .expectNext(user)
                .verifyComplete();
    }

    @Test
    @DisplayName("should throw UserNotFoundException when ID not found")
    void shouldThrowExceptionWhenUserNotFound() {
    
        Long userId = 1L;
        when(userRepository.getById(userId)).thenReturn(Mono.empty());

     
        StepVerifier.create(getUserUseCaseImpl.getById(userId))
                .expectError(UserNotFoundException.class)
                .verify();
    }
}
