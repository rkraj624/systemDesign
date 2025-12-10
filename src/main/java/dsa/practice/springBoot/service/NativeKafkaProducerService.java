package dsa.practice.springBoot.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
@ConditionalOnProperty(name = "kafka.enabled", havingValue = "true", matchIfMissing = false)
public class NativeKafkaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(NativeKafkaProducerService.class);

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    private KafkaProducer<String, String> producer;

    @PostConstruct
    public void init() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 3);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);

        producer = new KafkaProducer<>(props);
        logger.info("Native Kafka Producer initialized with bootstrap servers: {}", bootstrapServers);
    }

    public void sendMessageAsync(String topic, String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        
        producer.send(record, (RecordMetadata metadata, Exception exception) -> {
            if (exception != null) {
                logger.error("Error sending message to topic {}: {}", topic, exception.getMessage());
            } else {
                logger.info("Message sent successfully to topic {} [partition: {}, offset: {}]", 
                    topic, metadata.partition(), metadata.offset());
            }
        });
    }

    public RecordMetadata sendMessageSync(String topic, String key, String value) throws ExecutionException, InterruptedException {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        
        Future<RecordMetadata> future = producer.send(record);
        RecordMetadata metadata = future.get();
        
        logger.info("Message sent synchronously to topic {} [partition: {}, offset: {}]", 
            topic, metadata.partition(), metadata.offset());
        
        return metadata;
    }

    public void sendMessageWithPartition(String topic, int partition, String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, partition, key, value);
        
        producer.send(record, (RecordMetadata metadata, Exception exception) -> {
            if (exception != null) {
                logger.error("Error sending message to topic {} partition {}: {}", topic, partition, exception.getMessage());
            } else {
                logger.info("Message sent to topic {} [partition: {}, offset: {}]", 
                    topic, metadata.partition(), metadata.offset());
            }
        });
    }

    @PreDestroy
    public void cleanup() {
        if (producer != null) {
            logger.info("Closing Native Kafka Producer");
            producer.close();
        }
    }
}

