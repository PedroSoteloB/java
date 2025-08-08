package com.domain1.books.domain.ports.in;

import com.domain1.books.domain.model.Book;
import reactor.core.publisher.Mono;

public interface GetBookUseCase {
 Mono <Book> getById(Long id);

}
