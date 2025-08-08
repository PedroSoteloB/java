package com.pedro.borrowings.infraestructure.out.db.r2dbc;

import org.springframework.stereotype.Repository;

import com.pedro.borrowings.model.domain.Borrowing;
import com.pedro.borrowings.model.domain.ports.out.BorrowingRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class BorrowingReactiveRepository implements BorrowingRepository {

    private final BorrowingR2dbcRepository borrowingR2dbcRepository;
    private final BorrowingEntityMapper borrowingEntityMapper;

    @Override
    public Mono<Void> save(Borrowing borrowing) {
        return borrowingR2dbcRepository
                .save(borrowingEntityMapper.toEntity(borrowing))
                .then();
    }

    @Override
    public Mono<Void> update(Borrowing borrowing) {
        return borrowingR2dbcRepository
                .save(borrowingEntityMapper.toEntity(borrowing))
                .then();
    }

    @Override
    public Flux<Borrowing> findByUserId(Long userId) {
        return borrowingR2dbcRepository
                .findByUserId(userId)
                .map(borrowingEntityMapper::toDomain);
    }

    @Override
    public Mono<Borrowing> findById(Long id) {
        return borrowingR2dbcRepository
                .findById(id)
                .map(borrowingEntityMapper::toDomain);
    }
    @Override
    public Flux<Borrowing> findall() {
    return borrowingR2dbcRepository.findAll()
            .map(borrowingEntityMapper::toDomain);
}
}
