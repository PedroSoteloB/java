package com.pedro.borrowings.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.pedro.borrowings.infraestructure.handler.exceptions.NotFoundException;
import com.pedro.borrowings.proxy.users.api.UserApi;
import com.pedro.borrowings.proxy.users.dtos.UserDto;
import com.pedro.borrowings.services.impl.UserServiceImpl;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class UserServiceImplTest {

    private UserApi userApi;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userApi = mock(UserApi.class);
        userService = new UserServiceImpl(userApi);
    }

    @Test
    void validateUserExists_shouldComplete_whenUserExists() {
        Long userId = 1L;
        UserDto mockUser = new UserDto(userId, "Pedro", "Lopez", "123456789", "pedro@mail.com");

        when(userApi.getById(userId)).thenReturn(Mono.just(mockUser));

        StepVerifier.create(userService.validateUserExists(userId))
                .verifyComplete();

        verify(userApi).getById(userId);
    }

@Test
void validateUserExists_shouldError_whenUserNotFound() {
    Long userId = 99L;

    when(userApi.getById(userId)).thenReturn(Mono.empty());

    StepVerifier.create(userService.validateUserExists(userId))
            .expectErrorMatches(throwable ->
                    throwable instanceof NotFoundException &&
                    throwable.getMessage().contains("Usuario no encontrado"))
            .verify();

    verify(userApi).getById(userId);
}
}
