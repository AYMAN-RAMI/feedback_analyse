package com.feedback.analyse.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "feedbacks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 1000)
    private String contenu;
    
    @Column(nullable = false)
    private LocalDateTime dateSoumission;
    
    @Column
    private String categorie;
    
    @Column
    private String sentiment;
    
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    
    @OneToOne(mappedBy = "feedback", cascade = CascadeType.ALL)
    private AnalyseIA analyseIA;
}
