package com.feedback.analyse.service;

import com.feedback.analyse.dto.ProjectDTO;
import com.feedback.analyse.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Project createProject(ProjectDTO dto);

    Optional<Project> getProjectById(Long id);

    List<Project> getAllProjects();
    Project updateProject(Long id, ProjectDTO dto);
    void deleteProject(Long id);
}

