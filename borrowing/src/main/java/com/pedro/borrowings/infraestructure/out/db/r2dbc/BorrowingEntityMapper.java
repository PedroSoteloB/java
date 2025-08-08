package com.pedro.borrowings.infraestructure.out.db.r2dbc;

import org.mapstruct.Mapper;

import com.pedro.borrowings.model.domain.Borrowing;

@Mapper(componentModel = "spring")
public interface BorrowingEntityMapper {
    BorrowingEntity toEntity(Borrowing borrowing);
    Borrowing toDomain(BorrowingEntity entity);
}
