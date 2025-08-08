package com.pedro.users.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.domain1.users.aplication.SaveUserUseCaseImpl;
import com.domain1.users.domain.model.User;
import com.domain1.users.domain.ports.out.UserRepository;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class SaveUserUseCaseImplTest {

    @Mock
    private UserRepository userRepository;

    private SaveUserUseCaseImpl saveUserUseCase;

    @BeforeEach
    void setUp() {
        saveUserUseCase = new SaveUserUseCaseImpl(userRepository);
    }

    @Test
    @DisplayName("should save user and complete without error")
    void shouldSaveUserSuccessfully() {
        
        User user = new User(1L, "Pedro", "Sotelo", "pedro@gmail.com", "916256104");

        when(userRepository.save(user)).thenReturn(Mono.empty()); 

    
        StepVerifier.create(saveUserUseCase.save(user))
                .verifyComplete();

    
        verify(userRepository, times(1)).save(user);
    }
}
