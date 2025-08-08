
package com.domain1.books.aplication;

import com.domain1.books.domain.model.Book;
import com.domain1.books.domain.ports.in.SaveBookUseCase;
import com.domain1.books.domain.ports.out.BookRepository;
import com.domain1.books.exceptions.DuplicateIsbnException;

import reactor.core.publisher.Mono;

public record SaveBookUseCaseImpl(BookRepository bookRepository) implements SaveBookUseCase {

    @Override
    public Mono<Void> save(Book book) {
        return bookRepository
            .findByIsbn(book.getIsbn())
            .flatMap(existing -> {
                if (!existing.getId().equals(book.getId())) {
                    return Mono.error(new DuplicateIsbnException("ISBN ya existe"));
                }
                return bookRepository.save(book);
            })
            //.switchIfEmpty(bookRepository.save(book))
            .switchIfEmpty(Mono.defer(() -> bookRepository.save(book)))
            .then();
    }
}
