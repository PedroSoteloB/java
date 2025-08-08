package com.pedro.book.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.domain1.books.domain.model.Book;

public class BookTest {

    @Test
    void shouldCreateBookCorrectly() {
        // Arrange
        Long id = 1L;
        String title = "Clean Code";
        String author = "Robert C. Martin";
        String isbn = "123-456";
        boolean available = true;

        // Act
        Book book = new Book(id, title, author, isbn, available);

        // Assert
        assertEquals(id, book.getId());
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(isbn, book.getIsbn());
        assertTrue(book.isAvailable());
    }

    @Test
    void shouldCompareBooksByFieldValues() {
        Book b1 = new Book(1L, "Book", "Author", "111", true);
        Book b2 = new Book(1L, "Book", "Author", "111", true);

        assertEquals(b1.getId(), b2.getId());
        assertEquals(b1.getTitle(), b2.getTitle());
        assertEquals(b1.getAuthor(), b2.getAuthor());
        assertEquals(b1.getIsbn(), b2.getIsbn());
        assertEquals(b1.isAvailable(), b2.isAvailable());
    }
}