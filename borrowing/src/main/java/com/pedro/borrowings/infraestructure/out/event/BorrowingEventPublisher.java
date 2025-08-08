package com.pedro.borrowings.infraestructure.out.event;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BorrowingEventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${borrowing.kafka.topic}")
    private String topic;

    public void publishBookBorrowed(String title) {
        String message = "📚 Libro prestado: " + title;
        kafkaTemplate.send(topic, message);
    }
}
