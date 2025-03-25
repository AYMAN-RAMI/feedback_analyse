package com.feedback.analyse.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "analyses_ia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalyseIA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "feedback_id", nullable = false)
    private Feedback feedback;
    
    @Column
    private String sentiment;
    
    @Column
    private Float score;
    
    @Column
    private String typeDetecte;
    
    @Column
    private String recommandation;
    
    @Column
    private LocalDateTime dateAnalyse;
}
