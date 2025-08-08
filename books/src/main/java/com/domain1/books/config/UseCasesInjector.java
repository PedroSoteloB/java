package com.domain1.books.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.domain1.books.aplication.GetAllBooksUseCaseImpl;
import com.domain1.books.aplication.GetBookUseCaseImpl;
import com.domain1.books.aplication.SaveBookUseCaseImpl;
import com.domain1.books.domain.ports.in.GetAllBooksUseCase;
import com.domain1.books.domain.ports.in.GetBookUseCase;
import com.domain1.books.domain.ports.in.SaveBookUseCase;
import com.domain1.books.domain.ports.out.BookRepository;


@Configuration
public class UseCasesInjector {

       @Bean
    GetBookUseCase injectGetBookUseCase(BookRepository bookRepository) {
        return new GetBookUseCaseImpl(bookRepository);
    }

    @Bean
    SaveBookUseCase injectSaveBookUseCase(BookRepository bookRepository) {
        return new SaveBookUseCaseImpl(bookRepository);
    }

    @Bean
    GetAllBooksUseCase injectGetAllBooksUseCase(BookRepository bookRepository){
        return new GetAllBooksUseCaseImpl(bookRepository);
    }
}
