package com.feedback.analyse.controller;

import com.feedback.analyse.model.Utilisateur;
import com.feedback.analyse.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(loginRequest.getEmail())
                .orElse(null);

        if (utilisateur != null &&
                passwordEncoder.matches(loginRequest.getPassword(), utilisateur.getMotDePasse())) {

            Map<String, Object> response = new HashMap<>();
            response.put("token", UUID.randomUUID().toString());
            response.put("role", utilisateur.getRole());
            response.put("utilisateur", utilisateur); // pour envoyer id, nom, email
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(401).body("Email ou mot de passe incorrect");
    }

    // Classe interne pour recevoir les données de login
    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}
