package com.feedback.analyse.mapper;

import com.feedback.analyse.model.Utilisateur;
import com.feedback.analyse.dto.UtilisateurDTO;

public class UtilisateurMapper {

    public static UtilisateurDTO toDto(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }
        UtilisateurDTO dto = new UtilisateurDTO();
        dto.setId(utilisateur.getId());
        dto.setNom(utilisateur.getNom());
        dto.setEmail(utilisateur.getEmail());
        dto.setMotDePasse(utilisateur.getMotDePasse());
        dto.setRole(utilisateur.getRole());
        return dto;
    }
}
