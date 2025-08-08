// package com.pedro.borrowings.controller;

// import org.springframework.web.bind.annotation.*;

// import com.pedro.borrowings.dtos.BorrowingInDTO;
// import com.pedro.borrowings.dtos.BorrowingDTO;

// import jakarta.validation.Valid;
// import reactor.core.publisher.Flux;
// import reactor.core.publisher.Mono;

// @RequestMapping("/borrowings")
// public interface BorrowingController {

//     @PostMapping
//     Mono<Void> create(@Valid @RequestBody BorrowingInDTO borrowingInDTO);

//     @GetMapping("/user/{userId}")
//     Flux<BorrowingDTO> getByUser(@PathVariable Long userId);

//     @PatchMapping("/{id}/return")
//     Mono<Void> returnBook(@PathVariable Long id);

//     @GetMapping("/borrowings")
//     Flux<BorrowingDTO> getAll();
// }

package com.pedro.borrowings.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.pedro.borrowings.dtos.BorrowingInDTO;
import com.pedro.borrowings.dtos.BorrowingDTO;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping(value = "/borrowings", produces = MediaType.APPLICATION_JSON_VALUE)
public interface BorrowingController {

    @PostMapping
    Mono<Void> create(@Valid @RequestBody BorrowingInDTO borrowingInDTO);

    @GetMapping("/user/{userId}")
    Flux<BorrowingDTO> getByUser(@PathVariable Long userId);

    @PatchMapping("/{id}/return")
    Mono<Void> returnBook(@PathVariable Long id);

    @GetMapping
    Flux<BorrowingDTO> getAll(); 
}