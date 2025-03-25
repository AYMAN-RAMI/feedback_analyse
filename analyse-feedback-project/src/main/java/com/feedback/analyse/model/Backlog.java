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
    private String statut;
    
    @OneToMany(mappedBy = "backlog", cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}
