package com.feedback.analyse.service;

import com.feedback.analyse.model.Utilisateur;
import java.util.List;

public interface UtilisateurService {
    List<Utilisateur> getAllUtilisateurs();
    Utilisateur getUtilisateurById(Long id);
    Utilisateur getUtilisateurByEmail(String email);
    Utilisateur createUtilisateur(Utilisateur utilisateur);
    Utilisateur updateUtilisateur(Long id, Utilisateur utilisateur);
    void deleteUtilisateur(Long id);
    boolean existsByEmail(String email);
}
