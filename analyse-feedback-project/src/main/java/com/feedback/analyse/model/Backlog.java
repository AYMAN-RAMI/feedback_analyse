package com.feedback.analyse.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Entity
@Table(name = "backlogs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String code;  // Ex: US-001

    @Column
    private String titre;

    @Column(length = 1000)
    private String description;

    @Column
    private Integer points;

    @Column
    private String priorite; // Critique, Haute, Moyenne, Faible

    @Column
    private String statut;   // Nouveau, Prêt, En cours, Terminé

    @Column(name = "assigne_a")
    private String assigneA;

    @OneToMany(mappedBy = "backlog", cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}
