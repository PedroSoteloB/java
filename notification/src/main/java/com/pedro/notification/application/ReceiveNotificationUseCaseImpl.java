package com.pedro.notification.application;

import org.springframework.stereotype.Service;

import com.pedro.notification.domain.ports.in.ReceiveNotificationUseCase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReceiveNotificationUseCaseImpl implements ReceiveNotificationUseCase {

    @Override
    public void notify(String message) {
        log.info("🔔 Notificación recibida: {}", message);
    }
}
