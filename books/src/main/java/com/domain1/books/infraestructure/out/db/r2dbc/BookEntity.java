package com.domain1.books.infraestructure.out.db.r2dbc;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "books")
public class BookEntity {
    @Id
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Boolean available;
}
