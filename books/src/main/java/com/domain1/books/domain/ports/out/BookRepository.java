package com.domain1.books.domain.ports.out;

import com.domain1.books.domain.model.Book;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookRepository {
   Mono <Book> getById(Long Id);
   
   Mono<Void> save (Book book);
  
   Flux<Book> getAllBooks();

   Mono<Book> findByIsbn(String isbn);


}