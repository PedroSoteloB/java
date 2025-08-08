package com.pedro.borrowings.services.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.pedro.borrowings.config.BookClient;
import com.pedro.borrowings.dtos.BorrowingDTO;
import com.pedro.borrowings.dtos.BorrowingInDTO;
import com.pedro.borrowings.infraestructure.handler.exceptions.BadRequestException;
import com.pedro.borrowings.infraestructure.handler.exceptions.NotFoundException;
import com.pedro.borrowings.infraestructure.out.event.BorrowingEventPublisher;
import com.pedro.borrowings.model.domain.Borrowing;
import com.pedro.borrowings.model.domain.ports.out.BorrowingRepository;
import com.pedro.borrowings.services.BorrowingService;
import com.pedro.borrowings.services.UserService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {

    private final BorrowingRepository borrowingRepository;
    private final UserService userService;
    private final BookClient bookClient;
    private final BorrowingEventPublisher borrowingEventPublisher;
  

    @Override
    public Mono<Void> create(BorrowingInDTO borrowingInDTO) {
        Long userId = borrowingInDTO.userId();
        Long bookId = borrowingInDTO.bookId();

        return userService.validateUserExists(userId)
            .then(bookClient.getBookById(bookId))
            .flatMap(bookDTO -> {
                if (!bookDTO.available()) {
                    return Mono.error(new BadRequestException("Libro no disponible"));
                }

                LocalDateTime now = LocalDateTime.now();
                LocalDateTime dueDate = now.plusDays(10);

                Borrowing borrowing = Borrowing.builder()
                        .id(null)
                        .userId(userId)
                        .bookId(bookId)
                        .returned(false)
                        .borrowedAt(now)
                        .dueDate(dueDate)
                        .returnedAt(null) 
                        .build();

                return borrowingRepository.save(borrowing)
                        .then(bookClient.updateBookAvailability(bookId, false))
                        .then(publishBookBorrowedEvent(bookDTO.title()))
                        .then();
            });
    }

private Mono<Void> publishBookBorrowedEvent(String bookTitle) {
    return Mono.fromRunnable(() -> borrowingEventPublisher.publishBookBorrowed(bookTitle));
}

    @Override
    public Flux<BorrowingDTO> getByUserId(Long userId) {
        return borrowingRepository
                .findByUserId(userId)
                .map(borrowing -> new BorrowingDTO(
                        borrowing.getId(),
                        borrowing.getUserId(),
                        borrowing.getBookId(),
                        borrowing.getReturned(),
                        borrowing.getBorrowedAt(),
                        borrowing.getDueDate(),
                        borrowing.getReturnedAt()
                ));
    }

 @Override
public Mono<Void> returnBook(Long id) {
    return borrowingRepository
        .findById(id)
        .switchIfEmpty(Mono.error(new NotFoundException("Préstamo no encontrado")))
        .flatMap(borrowing -> {
            if (borrowing.getReturned()) {
                return Mono.error(new BadRequestException("El libro ya fue devuelto"));
            }

            borrowing.setReturned(true);
            borrowing.setReturnedAt(LocalDateTime.now());

            return borrowingRepository.save(borrowing)
                    .then(bookClient.updateBookAvailability(borrowing.getBookId(), true));
        });
}



@Override
    public Flux<BorrowingDTO> getAll() {
       return borrowingRepository.findall()
        .map(borrowing -> new BorrowingDTO(
            borrowing.getId(),
            borrowing.getUserId(),
            borrowing.getBookId(),
            borrowing.getReturned(),
            borrowing.getBorrowedAt(),
            borrowing.getDueDate(),
            borrowing.getReturnedAt()
        ));
    }

}