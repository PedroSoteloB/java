package com.pedro.borrowings.proxy.books.api.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.pedro.borrowings.proxy.books.api.BookApi;

import reactor.core.publisher.Mono;

@Component
public record BookApiImpl(@Qualifier("bookWebClient") WebClient webClient) implements BookApi {

    @Override
    public Mono<Boolean> isBookAvailable(Long bookId) {
        return webClient
                .get()
                .uri("/books/{id}", bookId)
                .retrieve()
                .bodyToMono(BookResponse.class)
                .map(BookResponse::available);
    }

    private record BookResponse(Boolean available) {}
}
