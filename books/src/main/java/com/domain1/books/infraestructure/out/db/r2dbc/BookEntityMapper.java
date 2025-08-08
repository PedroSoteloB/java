package com.domain1.books.infraestructure.out.db.r2dbc;

import org.mapstruct.Mapper;

import com.domain1.books.domain.model.Book;

@Mapper (componentModel = "spring")
public interface BookEntityMapper {

    Book bookEntityToBook(BookEntity bookEntity);
    BookEntity bookToBookEntity(Book book);
}


