package com.feedback.analyse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "clients")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // ⚠️ Corrige le problème Swagger
public class Client extends Utilisateur {

    @Column(nullable = false)
    private String entreprise;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // ⚠️ Évite les boucles infinies et erreurs JSON
    private List<Feedback> feedbacks;
}
