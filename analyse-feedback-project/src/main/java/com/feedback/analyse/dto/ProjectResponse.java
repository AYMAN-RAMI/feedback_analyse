package com.feedback.analyse.dto;

import java.util.List;
import lombok.Data;
@Data
public class ProjectResponse {
    private Long id;
    private String name;
    private Long productOwnerId;
    private Long scrumMasterId;
    private List<Long> developerIds;

    // Getters et Setters
}