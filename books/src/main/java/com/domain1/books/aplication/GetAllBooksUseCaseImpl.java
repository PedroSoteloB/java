package com.domain1.books.aplication;

import com.domain1.books.domain.ports.out.BookRepository;
import com.domain1.books.domain.model.Book;
import com.domain1.books.domain.ports.in.GetAllBooksUseCase;
import reactor.core.publisher.Flux;

public record GetAllBooksUseCaseImpl(BookRepository bookRepository) implements GetAllBooksUseCase{

    @Override
    public Flux<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

}

