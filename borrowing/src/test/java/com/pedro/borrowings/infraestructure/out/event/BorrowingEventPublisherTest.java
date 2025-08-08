package com.pedro.borrowings.infraestructure.out.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.kafka.core.KafkaTemplate;

public class BorrowingEventPublisherTest {

    private KafkaTemplate<String, String> kafkaTemplate;
    private BorrowingEventPublisher publisher;

    @BeforeEach
    void setUp() {
        kafkaTemplate = mock(KafkaTemplate.class);
        publisher = new BorrowingEventPublisher(kafkaTemplate);


        try {
            var field = BorrowingEventPublisher.class.getDeclaredField("topic");
            field.setAccessible(true);
            field.set(publisher, "notification"); 
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void publishBookBorrowed_sendsCorrectMessage() {
        String title = "Clean Code";
        String expectedMessage = "📚 Libro prestado: " + title;

        publisher.publishBookBorrowed(title);

        verify(kafkaTemplate, times(1)).send("notification", expectedMessage);
    }
}
