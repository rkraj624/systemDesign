package dsa.practice.springBoot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "kafka.enabled", havingValue = "true", matchIfMissing = false)
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "user-topic", groupId = "spring-boot-kafka-group")
    public void consumeUserTopic(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition, @Header(KafkaHeaders.OFFSET) long offset) {
        logger.info("Consumed message from user-topic [partition: {}, offset: {}]: {}", partition, offset, message);
    }

    @KafkaListener(topics = "order-topic", groupId = "spring-boot-kafka-group")
    public void consumeOrderTopic(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition, @Header(KafkaHeaders.OFFSET) long offset) {
        logger.info("Consumed message from order-topic [partition: {}, offset: {}]: {}", partition, offset, message);
    }
}
