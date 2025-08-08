package com.pedro.borrowings.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.RETURNS_SELF;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class BookClientTest {

    private WebClient mockWebClient;
    private WebClient.RequestBodyUriSpec mockUriSpec;
    private WebClient.ResponseSpec mockResponseSpec;
    private BookClient bookClient;

    @BeforeEach
    void setUp() {
        mockWebClient = mock(WebClient.class, RETURNS_DEEP_STUBS);
        mockUriSpec = mock(WebClient.RequestBodyUriSpec.class, RETURNS_DEEP_STUBS);
        mockResponseSpec = mock(WebClient.ResponseSpec.class);
        bookClient = new BookClient(mockWebClient);
    }

    @Test
    void getBookById_returnsBookDTO() {
        Long bookId = 1L;
        BookClient.BookDTO expectedBook = new BookClient.BookDTO(bookId, "Clean Code", "Robert C. Martin", true);

        when(mockWebClient.get()
                .uri("/books/{id}", bookId)
                .retrieve()
                .bodyToMono(BookClient.BookDTO.class))
                .thenReturn(Mono.just(expectedBook));

        StepVerifier.create(bookClient.getBookById(bookId))
                .expectNext(expectedBook)
                .verifyComplete();
    }

@SuppressWarnings("unchecked")
@Test
void updateBookAvailability_shouldReturnVoid() {
    Long bookId = 1L;

    WebClient.RequestBodyUriSpec mockRequestBodyUriSpec = mock(WebClient.RequestBodyUriSpec.class);
    WebClient.RequestHeadersSpec mockRequestHeadersSpec = mock(WebClient.RequestHeadersSpec.class);
    WebClient.ResponseSpec mockResponseSpec = mock(WebClient.ResponseSpec.class);

    when(mockWebClient.patch()).thenReturn(mockRequestBodyUriSpec);
    when(mockRequestBodyUriSpec.uri("/books/{id}/availability", bookId)).thenReturn(mockRequestBodyUriSpec);
    when(mockRequestBodyUriSpec.bodyValue(any())).thenReturn(mockRequestHeadersSpec);
    when(mockRequestHeadersSpec.retrieve()).thenReturn(mockResponseSpec);
    when(mockResponseSpec.bodyToMono(Void.class)).thenReturn(Mono.empty());

    StepVerifier.create(bookClient.updateBookAvailability(bookId, false))
            .verifyComplete();
}
}
