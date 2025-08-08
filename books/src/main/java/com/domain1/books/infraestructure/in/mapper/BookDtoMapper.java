package com.domain1.books.infraestructure.in.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.domain1.books.domain.model.Book;
import com.domain1.books.infraestructure.in.dtos.BookDTO;
import com.domain1.books.infraestructure.in.dtos.BookInDTO;
import com.domain1.books.infraestructure.in.dtos.BookPatchDTO;

@Mapper (componentModel="spring")
public interface BookDtoMapper {
     BookDTO bookToBookDto(Book book); 
     @Mapping (target ="id", ignore=true)
     @Mapping(target = "available", source = "available")
     Book bookInDtoTOBook(BookInDTO bookInDTO);

     @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
     void updateBookFromBookPatchDto(BookPatchDTO bookPatchDTO, @MappingTarget Book book);

}
