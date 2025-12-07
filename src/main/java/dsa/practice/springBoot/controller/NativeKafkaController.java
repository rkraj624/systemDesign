package dsa.practice.springBoot.controller;

import dsa.practice.springBoot.service.NativeKafkaConsumerService;
import dsa.practice.springBoot.service.NativeKafkaProducerService;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/native-kafka")
public class NativeKafkaController {

    @Autowired
    private NativeKafkaProducerService nativeKafkaProducerService;

    @Autowired
    private NativeKafkaConsumerService nativeKafkaConsumerService;

    @PostMapping("/publish-async")
    public ResponseEntity<Map<String, String>> publishMessageAsync(@RequestParam("topic") String topic, @RequestParam("key") String key, @RequestParam("message") String message) {

        nativeKafkaProducerService.sendMessageAsync(topic, key, message);

        Map<String, String> response = new HashMap<>();
        response.put("status", "Message sent asynchronously using native Kafka producer");
        response.put("topic", topic);
        response.put("key", key);
        response.put("message", message);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/publish-sync")
    public ResponseEntity<Map<String, Object>> publishMessageSync(@RequestParam("topic") String topic, @RequestParam("key") String key, @RequestParam("message") String message) {

        try {
            RecordMetadata metadata = nativeKafkaProducerService.sendMessageSync(topic, key, message);

            Map<String, Object> response = new HashMap<>();
            response.put("status", "Message sent synchronously using native Kafka producer");
            response.put("topic", topic);
            response.put("key", key);
            response.put("message", message);
            response.put("partition", metadata.partition());
            response.put("offset", metadata.offset());
            response.put("timestamp", metadata.timestamp());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "Error");
            response.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PostMapping("/publish-with-partition")
    public ResponseEntity<Map<String, String>> publishMessageWithPartition(@RequestParam("topic") String topic, @RequestParam("partition") int partition, @RequestParam("key") String key, @RequestParam("message") String message) {

        nativeKafkaProducerService.sendMessageWithPartition(topic, partition, key, message);

        Map<String, String> response = new HashMap<>();
        response.put("status", "Message sent to specific partition using native Kafka producer");
        response.put("topic", topic);
        response.put("partition", String.valueOf(partition));
        response.put("key", key);
        response.put("message", message);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/consumer/start")
    public ResponseEntity<Map<String, String>> startConsumer(@RequestParam("topics") String[] topics) {

        nativeKafkaConsumerService.startConsuming(topics);

        Map<String, String> response = new HashMap<>();
        response.put("status", "Native Kafka Consumer started");
        response.put("topics", String.join(", ", topics));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/consumer/stop")
    public ResponseEntity<Map<String, String>> stopConsumer() {

        nativeKafkaConsumerService.stopConsuming();

        Map<String, String> response = new HashMap<>();
        response.put("status", "Native Kafka Consumer stopped");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "Native Kafka service is running");
        response.put("producer", "org.apache.kafka.clients.producer.KafkaProducer");
        response.put("consumer", "org.apache.kafka.clients.consumer.KafkaConsumer");
        return ResponseEntity.ok(response);
    }
}
