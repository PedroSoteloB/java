package com.pedro.borrowings.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class BookClient {

    private final WebClient webClient;

    public BookClient(@Qualifier("bookWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<BookDTO> getBookById(Long bookId) {
        return webClient
                .get()
                .uri("/books/{id}", bookId)
                .retrieve()
                .bodyToMono(BookDTO.class);
    }

    public Mono<Void> updateBookAvailability(Long bookId, boolean available) {
        return webClient
                .patch()
                .uri("/books/{id}/availability", bookId)
                .bodyValue(new BookPatchDTO(available))
                .retrieve()
                .bodyToMono(Void.class);
    }

    public record BookDTO(Long id, String title, String author, boolean available) {}
    public record BookPatchDTO(boolean available) {}
}