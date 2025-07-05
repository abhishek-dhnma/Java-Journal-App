package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.model.SentimentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class KafkaTestProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostConstruct
    public void testSend() {
        kafkaTemplate.send("weekly_sentiment", "test@example.com", "🔥 Test message from startup");
        System.out.println("✅ Test Kafka message sent");
    }
}

