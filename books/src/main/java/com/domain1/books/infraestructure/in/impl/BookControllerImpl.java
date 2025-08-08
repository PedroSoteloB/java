package com.domain1.books.infraestructure.in.impl;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.domain1.books.domain.model.Book;
import com.domain1.books.domain.ports.in.GetBookUseCase;
import com.domain1.books.domain.ports.in.GetAllBooksUseCase;
import com.domain1.books.domain.ports.in.SaveBookUseCase;
import com.domain1.books.infraestructure.in.BookController;
import com.domain1.books.infraestructure.in.dtos.BookDTO;
import com.domain1.books.infraestructure.in.dtos.BookInDTO;
import com.domain1.books.infraestructure.in.dtos.BookPatchDTO;
import com.domain1.books.infraestructure.in.mapper.BookDtoMapper;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookControllerImpl implements BookController {

    private final GetBookUseCase getBookUseCase;
    private final SaveBookUseCase saveBookUseCase;
    private GetAllBooksUseCase getAllBooksUseCase;
    private final BookDtoMapper bookMapper;

    @Override
    public Mono<BookDTO> getById(Long id) {
        return getBookUseCase
                .getById(id)
                .map(bookMapper::bookToBookDto);
    }


    @Override
    public Mono<Void> create(@Valid BookInDTO bookInDTO) {
        Book book = bookMapper.bookInDtoTOBook(bookInDTO);
        return saveBookUseCase
                .save(book);
    }


@PatchMapping("/{id}/availability") 
@Override
public Mono<Void> update(@PathVariable Long id, @Valid @RequestBody BookPatchDTO bookPatchDTO) {
    return getBookUseCase
            .getById(id)
            .flatMap(book -> {
                bookMapper.updateBookFromBookPatchDto(bookPatchDTO, book);
                return saveBookUseCase.save(book);
            });
}

@Override
    public Flux<BookDTO> getAll() {
        return getAllBooksUseCase
                .getAllBooks()
                .map(bookMapper::bookToBookDto);
    }
}