package dsa.practice.springBoot.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@ConditionalOnProperty(name = "kafka.enabled", havingValue = "true", matchIfMissing = false)
public class NativeKafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(NativeKafkaConsumerService.class);

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    private KafkaConsumer<String, String> consumer;
    private ExecutorService executorService;
    private final AtomicBoolean running = new AtomicBoolean(false);

    @PostConstruct
    public void init() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId + "-native");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");

        consumer = new KafkaConsumer<>(props);
        logger.info("Native Kafka Consumer initialized with bootstrap servers: {}", bootstrapServers);
    }

    public void startConsuming(String... topics) {
        if (running.get()) {
            logger.warn("Consumer is already running");
            return;
        }

        consumer.subscribe(Arrays.asList(topics));
        running.set(true);

        executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            logger.info("Native Kafka Consumer started consuming from topics: {}", Arrays.toString(topics));
            
            while (running.get()) {
                try {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                    
                    for (ConsumerRecord<String, String> record : records) {
                        logger.info("Native Consumer - Topic: {}, Partition: {}, Offset: {}, Key: {}, Value: {}", 
                            record.topic(), record.partition(), record.offset(), record.key(), record.value());
                        
                        processRecord(record);
                    }
                } catch (Exception e) {
                    logger.error("Error while consuming messages: {}", e.getMessage());
                }
            }
        });
    }

    private void processRecord(ConsumerRecord<String, String> record) {
        logger.info("Processing message from topic {}: {}", record.topic(), record.value());
    }

    public void stopConsuming() {
        if (running.get()) {
            logger.info("Stopping Native Kafka Consumer");
            running.set(false);
            
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }

    @PreDestroy
    public void cleanup() {
        stopConsuming();
        
        if (consumer != null) {
            logger.info("Closing Native Kafka Consumer");
            consumer.close();
        }
    }
}
