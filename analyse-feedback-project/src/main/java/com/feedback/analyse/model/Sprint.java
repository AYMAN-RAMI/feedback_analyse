package com.feedback.analyse.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sprints")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dateDebut;

    @Column
    private LocalDateTime dateFin;

    @Column(nullable = false)
    private String statut; // 🆕 ajouté ici !

    @OneToMany(mappedBy = "sprint")
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
}