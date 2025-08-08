package com.domain1.books.infraestructure.out.db.r2dbc;

import org.springframework.stereotype.Repository;

import com.domain1.books.domain.model.Book;
import com.domain1.books.domain.ports.out.BookRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class BookReactiveRepository implements BookRepository {

    private final BookR2dbcRepository bookR2dbcRepository;
    private final BookEntityMapper bookEntityMapper;

    @Override
    public Flux<Book> getAllBooks() {
        return bookR2dbcRepository
                .findAll()
                .map(bookEntityMapper::bookEntityToBook);
    }

    @Override
    public Mono<Book> getById(Long id) {
        return bookR2dbcRepository
                .findById(id)
                .map(bookEntityMapper::bookEntityToBook);
    }

    @Override
    public Mono<Void> save(Book book) {
        return bookR2dbcRepository
                .save(bookEntityMapper.bookToBookEntity(book))
                .then();
    }

    @Override
    public Mono<Book> findByIsbn(String isbn) {
        return bookR2dbcRepository
                .findByIsbn(isbn)
                .map(bookEntityMapper::bookEntityToBook);
    }
    
}
