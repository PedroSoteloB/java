package com.domain1.users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.domain1.users.aplication.GetUserUseCaseImpl;
import com.domain1.users.aplication.SaveUserUseCaseImpl;
import com.domain1.users.domain.ports.in.GetUserUseCase;
import com.domain1.users.domain.ports.in.SaveUserUseCase;
import com.domain1.users.domain.ports.out.UserRepository;


@Configuration
public class UseCasesInjector {

       @Bean
    GetUserUseCase injectGetUserUseCase(UserRepository userRepository) {
        return new GetUserUseCaseImpl(userRepository);
    }

    @Bean
    SaveUserUseCase injectSaveUserUseCase(UserRepository userRepository) {
        return new SaveUserUseCaseImpl(userRepository);
    }
    

  
}
