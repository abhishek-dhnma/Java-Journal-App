package net.engineeringdigest.journalApp.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import net.engineeringdigest.journalApp.model.SentimentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SentimentConsumerService {


    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "weekly_sentiment", groupId = "weekly-sentiment-group")
    public void consume(SentimentData  sentimentData) {
        sendEmail(sentimentData);
    }

//    @KafkaListener(topics = "weekly_sentiment", groupId = "debug-group-1")
//    public void ccc(SentimentData  message) {
//        System.out.println("🐛 Got: " + message);
//    }


    private void sendEmail(SentimentData sentimentData){
        emailService.sendEmail(sentimentData.getEmail(), "Sentiment for previous week", sentimentData.getSentiment());
    }
}
