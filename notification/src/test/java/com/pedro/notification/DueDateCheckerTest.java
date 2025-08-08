// package com.pedro.notification;

// import java.time.LocalDateTime;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import static org.mockito.ArgumentMatchers.contains;
// import org.mockito.Mock;
// import static org.mockito.Mockito.atLeastOnce;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;
// import org.mockito.MockitoAnnotations;
// import org.springframework.web.reactive.function.client.WebClient;

// import com.pedro.notification.application.DueDateChecker;
// import com.pedro.notification.domain.ports.in.ReceiveNotificationUseCase;
// import com.pedro.notification.services.BorrowingDTO;

// import reactor.core.publisher.Flux;

// public class DueDateCheckerTest {

//     @Mock
//     private WebClient borrowingWebClient;

//     @Mock
//     private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

//     @Mock
//     private WebClient.RequestHeadersSpec requestHeadersSpec;

//     @Mock
//     private WebClient.ResponseSpec responseSpec;

//     @Mock
//     private ReceiveNotificationUseCase notificationUseCase;

//     private DueDateChecker dueDateChecker;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//         dueDateChecker = new DueDateChecker(borrowingWebClient, notificationUseCase);

//         when(borrowingWebClient.get()).thenReturn(requestHeadersUriSpec);
//         when(requestHeadersUriSpec.uri("/borrowings")).thenReturn(requestHeadersSpec);
//         when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
//     }

//     @Test
//     void testCheckNearDueBorrowings() {
//         BorrowingDTO borrowing = new BorrowingDTO(
//                 1L,
//                 42L,
//                 100L,
//                 false,
//                 LocalDateTime.now().minusDays(5),
//                 LocalDateTime.now().plusDays(1),
//                 null
//         );

//         when(responseSpec.bodyToFlux(BorrowingDTO.class)).thenReturn(Flux.just(borrowing));

//         dueDateChecker.checkNearDueBorrowings();

//         verify(notificationUseCase, atLeastOnce()).notify(contains("faltan 1 día"));
//     }
// }
