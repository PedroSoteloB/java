package com.pedro.borrowings.services.impl;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.pedro.borrowings.config.BookClient;
import com.pedro.borrowings.dtos.BorrowingInDTO;
import com.pedro.borrowings.infraestructure.handler.exceptions.BadRequestException;
import com.pedro.borrowings.infraestructure.handler.exceptions.NotFoundException;
import com.pedro.borrowings.infraestructure.out.event.BorrowingEventPublisher;
import com.pedro.borrowings.model.domain.Borrowing;
import com.pedro.borrowings.model.domain.ports.out.BorrowingRepository;
import com.pedro.borrowings.services.UserService;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class BorrowingServiceImplTest {

    @Mock
    private BorrowingRepository borrowingRepository;

    @Mock
    private UserService userService;

    @Mock
    private BookClient bookClient;

    @Mock
    private BorrowingEventPublisher borrowingEventPublisher;

    @InjectMocks
    private BorrowingServiceImpl borrowingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateBorrowingSuccessfully() {
     
        BorrowingInDTO input = new BorrowingInDTO(1L, 2L);
        when(userService.validateUserExists(1L)).thenReturn(Mono.empty());
        when(bookClient.getBookById(2L)).thenReturn(Mono.just(new BookClient.BookDTO(2L, "Title", "Author", true)));
        when(borrowingRepository.save(any(Borrowing.class))).thenReturn(Mono.empty());
        when(bookClient.updateBookAvailability(2L, false)).thenReturn(Mono.empty());

    
        StepVerifier.create(borrowingService.create(input))
                .verifyComplete();

        verify(borrowingEventPublisher).publishBookBorrowed("Title");
    }

 @Test
void shouldThrowWhenUserNotFound() {
 
    BorrowingInDTO input = new BorrowingInDTO(99L, 2L);

    when(userService.validateUserExists(99L))
        .thenReturn(Mono.error(new NotFoundException("Usuario no encontrado")));

    when(bookClient.getBookById(2L)) 
        .thenReturn(Mono.empty());

    
    StepVerifier.create(borrowingService.create(input))
        .expectErrorMatches(throwable -> throwable instanceof NotFoundException &&
            throwable.getMessage().contains("Usuario no encontrado"))
        .verify();
}
    @Test
    void shouldThrowWhenBookNotAvailable() {
       
        BorrowingInDTO input = new BorrowingInDTO(1L, 2L);
        when(userService.validateUserExists(1L)).thenReturn(Mono.empty());
        when(bookClient.getBookById(2L)).thenReturn(Mono.just(new BookClient.BookDTO(2L, "Title", "Author", false)));

    
        StepVerifier.create(borrowingService.create(input))
                .expectError(BadRequestException.class)
                .verify();
    }

    @Test
    void shouldReturnBookSuccessfully() {
        
        Borrowing borrowing = Borrowing.builder()
                .id(1L)
                .userId(1L)
                .bookId(2L)
                .returned(false)
                .borrowedAt(LocalDateTime.now())
                .dueDate(LocalDateTime.now().plusDays(10))
                .build();

        when(borrowingRepository.findById(1L)).thenReturn(Mono.just(borrowing));
        when(borrowingRepository.save(any(Borrowing.class))).thenReturn(Mono.empty());
        when(bookClient.updateBookAvailability(2L, true)).thenReturn(Mono.empty());

    
        StepVerifier.create(borrowingService.returnBook(1L))
                .verifyComplete();
    }

    @Test
    void shouldFailWhenBookAlreadyReturned() {
        Borrowing borrowing = Borrowing.builder()
                .id(1L)
                .userId(1L)
                .bookId(2L)
                .returned(true)
                .borrowedAt(LocalDateTime.now())
                .dueDate(LocalDateTime.now().plusDays(10))
                .returnedAt(LocalDateTime.now())
                .build();

        when(borrowingRepository.findById(1L)).thenReturn(Mono.just(borrowing));

        StepVerifier.create(borrowingService.returnBook(1L))
                .expectError(BadRequestException.class)
                .verify();
    }

    @Test
    void shouldFailWhenBorrowingNotFound() {
        when(borrowingRepository.findById(1L)).thenReturn(Mono.empty());

        StepVerifier.create(borrowingService.returnBook(1L))
                .expectError(NotFoundException.class)
                .verify();
    }
}
