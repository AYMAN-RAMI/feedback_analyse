package com.feedback.analyse.dto;

import java.util.List;

public class ProjectDTO {
    private String name;
    private Long productOwnerId;
    private Long scrumMasterId;
    private List<Long> developerIds;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProductOwnerId() {
        return productOwnerId;
    }

    public void setProductOwnerId(Long productOwnerId) {
        this.productOwnerId = productOwnerId;
    }

    public Long getScrumMasterId() {
        return scrumMasterId;
    }

    public void setScrumMasterId(Long scrumMasterId) {
        this.scrumMasterId = scrumMasterId;
    }

    public List<Long> getDeveloperIds() {
        return developerIds;
    }

    public void setDeveloperIds(List<Long> developerIds) {
        this.developerIds = developerIds;
    }
}
