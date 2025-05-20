package com.feedback.analyse.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "feedback_requests")
public class FeedbackRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Utilisateur po;

    @ManyToOne
    private Utilisateur client;

    private LocalDate requestDate;

    // --- Getters et Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Utilisateur getPo() { return po; }
    public void setPo(Utilisateur po) { this.po = po; }

    public Utilisateur getClient() { return client; }
    public void setClient(Utilisateur client) { this.client = client; }

    public LocalDate getRequestDate() { return requestDate; }
    public void setRequestDate(LocalDate requestDate) { this.requestDate = requestDate; }
}
