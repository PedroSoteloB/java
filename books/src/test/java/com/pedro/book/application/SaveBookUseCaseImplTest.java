package com.pedro.book.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.domain1.books.aplication.SaveBookUseCaseImpl;
import com.domain1.books.domain.model.Book;
import com.domain1.books.domain.ports.out.BookRepository;
import com.domain1.books.exceptions.DuplicateIsbnException;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class SaveBookUseCaseImplTest {

    private BookRepository bookRepository;
    private SaveBookUseCaseImpl saveBookUseCase;

    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class);
        saveBookUseCase = new SaveBookUseCaseImpl(bookRepository);
    }

    @Test
    void shouldSaveNewBookWhenIsbnIsUnique() {

        Book newBook = new Book(null, "Clean Code", "Robert C. Martin", "123-456", true);

        when(bookRepository.findByIsbn("123-456")).thenReturn(Mono.empty());
        when(bookRepository.save(newBook)).thenReturn(Mono.empty());


        StepVerifier.create(saveBookUseCase.save(newBook))
            .verifyComplete();

        verify(bookRepository).save(newBook);
    }

@Test
void shouldThrowExceptionWhenIsbnExistsWithDifferentId() {
    
    Book existingBook = new Book(1L, "Clean Code", "Robert C. Martin", "123-456", true);
    Book newBook = new Book(2L, "Another Book", "Someone", "123-456", true);

    when(bookRepository.findByIsbn("123-456")).thenReturn(Mono.just(existingBook));

    when(bookRepository.save(any(Book.class))).thenReturn(Mono.empty());

    StepVerifier.create(saveBookUseCase.save(newBook))
        .expectError(DuplicateIsbnException.class)
        .verify();

    verify(bookRepository, never()).save(any());
}

@Test
void shouldUpdateBookWhenIsbnExistsWithSameId() {
    
    Book existingBook = new Book(1L, "Clean Code", "Robert C. Martin", "123-456", true);
    Book updatedBook = new Book(1L, "Clean Code Updated", "Robert C. Martin", "123-456", false);

    when(bookRepository.findByIsbn("123-456")).thenReturn(Mono.just(existingBook));
    when(bookRepository.save(any(Book.class))).thenReturn(Mono.empty()); 

    
    StepVerifier.create(saveBookUseCase.save(updatedBook))
        .verifyComplete();

    verify(bookRepository, times(2)).save(any(Book.class)); 
}

}
