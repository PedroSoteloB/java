package com.pedro.borrowings.controller.impl;


import org.springframework.web.bind.annotation.RestController;

import com.pedro.borrowings.controller.BorrowingController;
import com.pedro.borrowings.dtos.BorrowingDTO;
import com.pedro.borrowings.dtos.BorrowingInDTO;
import com.pedro.borrowings.services.BorrowingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class BorrowingControllerImpl implements BorrowingController {

    private final BorrowingService borrowingService;

    @Override
    public Mono<Void> create(@Valid BorrowingInDTO borrowingInDTO) {
        return borrowingService.create(borrowingInDTO);
    }

    @Override
    public Flux<BorrowingDTO> getByUser(Long userId) {
        return borrowingService.getByUserId(userId);
    }

    @Override
    public Mono<Void> returnBook(Long id) {
        return borrowingService.returnBook(id);
    }
    @Override
    public Flux<BorrowingDTO> getAll() {
    return borrowingService.getAll();
}
}
