package dsa.practice.springBoot.controller;

import dsa.practice.springBoot.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/kafka")
@ConditionalOnProperty(name = "kafka.enabled", havingValue = "true", matchIfMissing = false)
public class KafkaController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/publish")
    public ResponseEntity<Map<String, String>> publishMessage(@RequestParam("topic") String topic, @RequestParam("message") String message) {

        kafkaProducerService.sendMessage(topic, message);

        Map<String, String> response = new HashMap<>();
        response.put("status", "Message sent to Kafka topic: " + topic);
        response.put("message", message);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/publish-with-key")
    public ResponseEntity<Map<String, String>> publishMessageWithKey(@RequestParam("topic") String topic, @RequestParam("key") String key, @RequestParam("message") String message) {

        kafkaProducerService.sendMessageWithKey(topic, key, message);

        Map<String, String> response = new HashMap<>();
        response.put("status", "Message sent to Kafka topic: " + topic);
        response.put("key", key);
        response.put("message", message);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/publish-json")
    public ResponseEntity<Map<String, String>> publishJsonMessage(@RequestParam("topic") String topic, @RequestBody Map<String, Object> data) {

        kafkaProducerService.sendObject(topic, data);

        Map<String, String> response = new HashMap<>();
        response.put("status", "JSON object sent to Kafka topic: " + topic);
        response.put("data", data.toString());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/publish-json-with-key")
    public ResponseEntity<Map<String, String>> publishJsonMessageWithKey(@RequestParam("topic") String topic, @RequestParam("key") String key, @RequestBody Map<String, Object> data) {

        kafkaProducerService.sendObjectWithKey(topic, key, data);

        Map<String, String> response = new HashMap<>();
        response.put("status", "JSON object sent to Kafka topic: " + topic);
        response.put("key", key);
        response.put("data", data.toString());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "Kafka service is running");
        return ResponseEntity.ok(response);
    }
}
