package com.domain1.books.domain.ports.in;
import reactor.core.publisher.Mono;

import com.domain1.books.domain.model.Book;

public interface SaveBookUseCase {
   Mono  <Void> save (Book book);

}
