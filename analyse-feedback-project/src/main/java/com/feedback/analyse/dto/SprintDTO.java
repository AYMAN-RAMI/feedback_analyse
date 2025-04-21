package com.feedback.analyse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SprintDTO {
    private Long id;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String statut;
    private Long ticketId;           // ✅ pour un seul ticket lié
    private List<Long> ticketIds;
}
