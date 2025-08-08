package com.pedro.notification.domain.ports.in;

public interface ReceiveNotificationUseCase {
    void notify(String message);
}