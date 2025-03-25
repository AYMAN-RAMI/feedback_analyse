package com.feedback.analyse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalyseIADTO {
    private Long id;
    private Long feedbackId;
    private String sentiment;
    private Float score;
    private String typeDetecte;
    private String recommandation;
    private LocalDateTime dateAnalyse;
}
