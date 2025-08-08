package com.domain1.books.domain.ports.in;

import com.domain1.books.domain.model.Book;

import reactor.core.publisher.Flux;

public interface GetAllBooksUseCase {

    Flux<Book> getAllBooks();

}
