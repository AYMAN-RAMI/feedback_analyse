package com.feedback.analyse.controller;

import com.feedback.analyse.mapper.UtilisateurMapper;

import com.feedback.analyse.dto.UtilisateurDTO;
import com.feedback.analyse.model.Utilisateur;
import com.feedback.analyse.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid; // Changé de javax.validation à jakarta.validation
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {
    private final UtilisateurService utilisateurService;

    @GetMapping
    public ResponseEntity<List<UtilisateurDTO>> getAllUtilisateurs() {
        List<UtilisateurDTO> utilisateurs = utilisateurService.getAllUtilisateurs().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(utilisateurs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> getUtilisateurById(@PathVariable Long id) {
        Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);
        return ResponseEntity.ok(convertToDTO(utilisateur));
    }

    @PostMapping
    public ResponseEntity<UtilisateurDTO> createUtilisateur(@Valid @RequestBody UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = convertToEntity(utilisateurDTO);
        Utilisateur newUtilisateur = utilisateurService.createUtilisateur(utilisateur);
        return new ResponseEntity<>(convertToDTO(newUtilisateur), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> updateUtilisateur(
            @PathVariable Long id,
            @Valid @RequestBody UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = convertToEntity(utilisateurDTO);
        Utilisateur updatedUtilisateur = utilisateurService.updateUtilisateur(id, utilisateur);
        return ResponseEntity.ok(convertToDTO(updatedUtilisateur));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.noContent().build();
    }

    private UtilisateurDTO convertToDTO(Utilisateur utilisateur) {
        UtilisateurDTO dto = new UtilisateurDTO();
        dto.setId(utilisateur.getId());
        dto.setNom(utilisateur.getNom());
        dto.setEmail(utilisateur.getEmail());
        dto.setRole(utilisateur.getRole());
        // Ne pas inclure le mot de passe dans la réponse pour des raisons de sécurité
        return dto;
    }

    private Utilisateur convertToEntity(UtilisateurDTO dto) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(dto.getId());
        utilisateur.setNom(dto.getNom());
        utilisateur.setEmail(dto.getEmail());
        utilisateur.setRole(dto.getRole());
        // Assurez-vous que le mot de passe est correctement géré (haché, etc.)
        utilisateur.setMotDePasse(dto.getMotDePasse()); // À remplacer par une logique de hachage si nécessaire
        return utilisateur;
    }
    @GetMapping("/clients")
    public List<UtilisateurDTO> getAllClients() {
        List<Utilisateur> clients = utilisateurService.getUtilisateursByRole("CLIENT");
        return clients.stream()
                .map(UtilisateurMapper::toDto)
                .collect(Collectors.toList());
    }
}