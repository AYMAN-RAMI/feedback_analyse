package com.feedback.analyse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BacklogDTO {

    private Long id;
    private String code;
    private String titre;
    private String description;
    private Integer points;
    private String priorite;
    private String statut;
    private String assigneA;

    private List<Long> ticketIds;
}
