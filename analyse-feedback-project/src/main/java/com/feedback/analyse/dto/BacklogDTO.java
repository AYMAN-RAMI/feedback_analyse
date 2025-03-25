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
    private String statut;
    private List<Long> ticketIds;
}
