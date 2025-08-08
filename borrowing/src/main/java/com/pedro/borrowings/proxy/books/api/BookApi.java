
package com.pedro.borrowings.proxy.books.api;

import reactor.core.publisher.Mono;

public interface BookApi {
    Mono<Boolean> isBookAvailable(Long bookId);
}
