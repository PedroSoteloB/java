package com.domain1.books.infraestructure.in;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.domain1.books.infraestructure.in.dtos.BookDTO;
import com.domain1.books.infraestructure.in.dtos.BookInDTO;
import com.domain1.books.infraestructure.in.dtos.BookPatchDTO;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RequestMapping("/books")
@Validated
public interface BookController {
   @GetMapping("/{id}")
    Mono<BookDTO> getById(@PathVariable Long id);

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    Mono<Void> create(@Valid @RequestBody BookInDTO BookInDTO);

    @PatchMapping("/{id}")
    Mono<Void> update(@PathVariable Long id, @Valid @RequestBody BookPatchDTO bookPatchDTO);

    @GetMapping("")
    Flux<BookDTO> getAll();
  

}
