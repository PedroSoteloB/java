

package com.pedro.notification.application;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.pedro.notification.domain.ports.in.ReceiveNotificationUseCase;
import com.pedro.notification.services.BorrowingDTO;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
@Slf4j
public class DueDateChecker {

    private final WebClient borrowingWebClient;
    private final ReceiveNotificationUseCase notificationUseCase;

    @PostConstruct
    public void checkNearDueBorrowings() {
        Flux.interval(Duration.ofSeconds(30))
            .flatMap(tick -> getAllBorrowings())
            .filter(borrowing -> !borrowing.returned()) 
            .doOnNext(this::evaluateDueDate)
            .onErrorContinue((e, o) -> log.error("Error verificando préstamos: {}", e.getMessage()))
            .subscribe();
    }

    private Flux<BorrowingDTO> getAllBorrowings() {
        return borrowingWebClient.get()
                .uri("/borrowings")
                .retrieve()
                .bodyToFlux(BorrowingDTO.class)
                .onErrorResume(err -> {
                    log.error("Error al consultar todos los préstamos: {}", err.getMessage());
                    return Flux.empty();
                });
    }

    private void evaluateDueDate(BorrowingDTO borrowing) {
        long hoursLeft = Duration.between(LocalDateTime.now(), borrowing.dueDate()).toHours();
        long daysLeft = (long) Math.ceil(hoursLeft / 24.0);

        String prefix = "⏰ Préstamo por vencer (Usuario %d): ".formatted(borrowing.userId());

        if (daysLeft == 2) {
            notificationUseCase.notify(prefix + "faltan 2 días.");
        } else if (daysLeft == 1) {
            notificationUseCase.notify(prefix + "falta 1 día.");
        }
    }
}