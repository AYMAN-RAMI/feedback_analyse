package com.feedback.analyse.dto;

import java.time.LocalDate;

public class NotificationDTO {

    private Long id;
    private String contenu;
    private LocalDate dateEnvoi;
    private String destinataireNom;

    // --- Getters et Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDate getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(LocalDate dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public String getDestinataireNom() {
        return destinataireNom;
    }

    public void setDestinataireNom(String destinataireNom) {
        this.destinataireNom = destinataireNom;
    }
}
