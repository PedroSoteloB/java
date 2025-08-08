package com.pedro.book.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.domain1.books.aplication.GetBookUseCaseImpl;
import com.domain1.books.domain.model.Book;
import com.domain1.books.domain.ports.out.BookRepository;
import com.domain1.books.exceptions.BookNotFoundException;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class GetBookUseCaseImplTest {

    private BookRepository bookRepository;
    private GetBookUseCaseImpl getBookUseCase;

    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class);
        getBookUseCase = new GetBookUseCaseImpl(bookRepository);
    }

    @Test
    void shouldReturnBookWhenExists() {
      
        Long bookId = 1L;
        Book expectedBook = new Book(bookId, "Clean Architecture", "Robert C. Martin", "111-222", true);

        when(bookRepository.getById(bookId)).thenReturn(Mono.just(expectedBook));

        
        StepVerifier.create(getBookUseCase.getById(bookId))
            .expectNext(expectedBook)
            .verifyComplete();

        verify(bookRepository).getById(bookId);
    }

    @Test
    void shouldThrowExceptionWhenBookNotFound() {
       
        Long bookId = 99L;
        when(bookRepository.getById(bookId)).thenReturn(Mono.empty());

   
        StepVerifier.create(getBookUseCase.getById(bookId))
            .expectError(BookNotFoundException.class)
            .verify();

        verify(bookRepository).getById(bookId);
    }
}
