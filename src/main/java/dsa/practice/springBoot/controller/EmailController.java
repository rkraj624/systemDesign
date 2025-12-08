package dsa.practice.springBoot.controller;

import dsa.practice.springBoot.model.EmailRequest;
import dsa.practice.springBoot.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            if (emailRequest.getRecipients() != null && emailRequest.getRecipients().length > 0) {
                emailService.sendSimpleEmail(emailRequest.getRecipients(), emailRequest.getSubject(), emailRequest.getBody());
            } else {
                emailService.sendSimpleEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody());
            }
            
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Email sent successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping("/send-html")
    public ResponseEntity<Map<String, String>> sendHtmlEmail(@RequestBody EmailRequest emailRequest) {
        try {
            emailService.sendHtmlEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody());
            
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "HTML email sent successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "Email service is running");
        return ResponseEntity.ok(response);
    }
}
