package com.pedro.notification.infraestructure.in.rest;

import com.pedro.notification.domain.ports.in.ReceiveNotificationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notificaciones")
@RequiredArgsConstructor
public class NotificationController {

    private final ReceiveNotificationUseCase notificationUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void receiveNotification(@RequestBody String message) {
        notificationUseCase.notify(message);
    }
}