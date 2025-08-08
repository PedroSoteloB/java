package com.pedro.notification.infraestructure.in.kafka;

import com.pedro.notification.domain.ports.in.ReceiveNotificationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationListener {

    private final ReceiveNotificationUseCase notificationUseCase;

    @KafkaListener(topics = "notification", groupId = "notification")
    public void processMessage(String content) {
        notificationUseCase.notify(content);
    }
}