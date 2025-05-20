package com.feedback.analyse.controller;

import com.feedback.analyse.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/email") // Pas besoin de répéter email dans @PostMapping
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-feedback-request")
    public ResponseEntity<Map<String, String>> sendFeedbackRequest(@RequestParam Long poId, @RequestParam Long clientId) {
        System.out.println("PO ID reçu = " + poId);
        System.out.println("Client ID reçu = " + clientId);

        // 🛠 APPEL du vrai service qui envoie l'email et enregistre en base
        emailService.sendFeedbackRequestEmail(poId, clientId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Email envoyé au client avec succès !");
        return ResponseEntity.ok(response);
    }

}
