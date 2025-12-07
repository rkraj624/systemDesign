package dsa.practice.springBoot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String topic, String message) {
        logger.info("Sending message to topic {}: {}", topic, message);
        
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, message);
        
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                logger.info("Message sent successfully to topic {} with offset {}", topic, result.getRecordMetadata().offset());
            } else {
                logger.error("Failed to send message to topic {}: {}", topic, ex.getMessage());
            }
        });
    }

    public void sendMessageWithKey(String topic, String key, String message) {
        logger.info("Sending message with key {} to topic {}: {}", key, topic, message);
        
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, key, message);
        
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                logger.info("Message with key {} sent successfully to topic {} with offset {}", key, topic, result.getRecordMetadata().offset());
            } else {
                logger.error("Failed to send message with key {} to topic {}: {}", key, topic, ex.getMessage());
            }
        });
    }

    public <T> void sendObject(String topic, T object) {
        logger.info("Sending object to topic {}: {}", topic, object);
        
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, object);
        
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                logger.info("Object sent successfully to topic {} with offset {}", topic, result.getRecordMetadata().offset());
            } else {
                logger.error("Failed to send object to topic {}: {}", topic, ex.getMessage());
            }
        });
    }

    public <T> void sendObjectWithKey(String topic, String key, T object) {
        logger.info("Sending object with key {} to topic {}: {}", key, topic, object);
        
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, key, object);
        
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                logger.info("Object with key {} sent successfully to topic {} with offset {}", key, topic, result.getRecordMetadata().offset());
            } else {
                logger.error("Failed to send object with key {} to topic {}: {}", key, topic, ex.getMessage());
            }
        });
    }
}
