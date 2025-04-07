// Créer ce fichier : src/main/java/com/feedback/analyse/controller/AuthController.java
package com.feedback.analyse.controller;

import com.feedback.analyse.model.Utilisateur;
import com.feedback.analyse.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(loginRequest.getEmail())
                .orElse(null);

        if (utilisateur != null && utilisateur.getMotDePasse().equals(loginRequest.getPassword())) {
            Map<String, Object> response = new HashMap<>();
            response.put("token", UUID.randomUUID().toString()); // Générer un token simple
            response.put("role", utilisateur.getRole());
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(401).body("Email ou mot de passe incorrect");
    }

    // Classe pour la requête de login
    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
