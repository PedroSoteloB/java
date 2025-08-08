package com.domain1.books.aplication;



import com.domain1.books.domain.model.Book;
import com.domain1.books.domain.ports.in.GetBookUseCase;
import com.domain1.books.domain.ports.out.BookRepository;
import com.domain1.books.exceptions.BookNotFoundException;

import reactor.core.publisher.Mono;

public record GetBookUseCaseImpl (BookRepository bookRepository)implements GetBookUseCase{ 
    @Override
    public Mono <Book> getById(Long id){
        return bookRepository
                             .getById(id)
                             .switchIfEmpty(Mono.error(new BookNotFoundException("Libro no encontrado")));
                          
    }
}
