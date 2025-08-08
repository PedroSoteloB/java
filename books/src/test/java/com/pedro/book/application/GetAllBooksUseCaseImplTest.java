package com.pedro.book.application;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.domain1.books.aplication.GetAllBooksUseCaseImpl;
import com.domain1.books.domain.model.Book;
import com.domain1.books.domain.ports.out.BookRepository;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class GetAllBooksUseCaseImplTest {

    private BookRepository bookRepository;
    private GetAllBooksUseCaseImpl getAllBooksUseCase;

    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class);
        getAllBooksUseCase = new GetAllBooksUseCaseImpl(bookRepository);
    }

    @Test
    void shouldReturnAllBooks() {

        Book book1 = new Book(1L, "Clean Code", "Robert C. Martin", "123-456", true);
        Book book2 = new Book(2L, "Domain-Driven Design", "Eric Evans", "789-101", true);

        when(bookRepository.getAllBooks()).thenReturn(Flux.fromIterable(List.of(book1, book2)));


        StepVerifier.create(getAllBooksUseCase.getAllBooks())
            .expectNext(book1)
            .expectNext(book2)
            .verifyComplete();

        verify(bookRepository, times(1)).getAllBooks();
    }
}
