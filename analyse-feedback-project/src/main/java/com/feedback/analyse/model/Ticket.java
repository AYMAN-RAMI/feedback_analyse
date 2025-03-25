package com.feedback.analyse.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String titre;
    
    @Column(nullable = false, length = 1000)
    private String description;
    
    @Column(nullable = false)
    private String statut;
    
    @Column
    private String priorite;
    
    @ManyToOne
    @JoinColumn(name = "backlog_id")
    private Backlog backlog;
    
    @ManyToOne
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;
    
    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private Utilisateur assignee;
}
