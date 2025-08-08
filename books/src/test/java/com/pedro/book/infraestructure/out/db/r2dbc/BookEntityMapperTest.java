package com.pedro.book.infraestructure.out.db.r2dbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.domain1.books.domain.model.Book;
import com.domain1.books.infraestructure.out.db.r2dbc.BookEntity;
import com.domain1.books.infraestructure.out.db.r2dbc.BookEntityMapper;

public class BookEntityMapperTest {

    private final BookEntityMapper mapper = Mappers.getMapper(BookEntityMapper.class);

    @Test
    void shouldMapToDomainCorrectly() {
        BookEntity entity = new BookEntity(1L, "Clean Code", "Robert C. Martin", "123-456", true);

        Book book = mapper.bookEntityToBook(entity);

        assertEquals(entity.getId(), book.getId());
        assertEquals(entity.getTitle(), book.getTitle());
        assertEquals(entity.getAuthor(), book.getAuthor());
        assertEquals(entity.getIsbn(), book.getIsbn());
        assertEquals(entity.getAvailable(), book.isAvailable());
    }

    @Test
    void shouldMapToEntityCorrectly() {
        Book book = new Book(2L, "Refactoring", "Martin Fowler", "789-101", false);

        BookEntity entity = mapper.bookToBookEntity(book);

        assertEquals(book.getId(), entity.getId());
        assertEquals(book.getTitle(), entity.getTitle());
        assertEquals(book.getAuthor(), entity.getAuthor());
        assertEquals(book.getIsbn(), entity.getIsbn());
        assertEquals(book.isAvailable(), entity.getAvailable());
    }
}
