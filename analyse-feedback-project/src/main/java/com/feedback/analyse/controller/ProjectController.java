package com.feedback.analyse.controller;

import com.feedback.analyse.dto.ProjectDTO;
import com.feedback.analyse.dto.ProjectResponse;
import com.feedback.analyse.model.Project;
import com.feedback.analyse.model.Utilisateur;
import com.feedback.analyse.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects() {
        List<ProjectResponse> responses = projectService.getAllProjects().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id) {
        Project project = projectService.getProjectById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return ResponseEntity.ok(convertToResponse(project));
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody ProjectDTO dto) {
        Project newProject = projectService.createProject(dto);
        return new ResponseEntity<>(convertToResponse(newProject), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long id,
                                                         @Valid @RequestBody ProjectDTO dto) {
        Project updatedProject = projectService.updateProject(id, dto);
        return ResponseEntity.ok(convertToResponse(updatedProject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    private ProjectResponse convertToResponse(Project project) {
        ProjectResponse response = new ProjectResponse();
        response.setId(project.getId());
        response.setName(project.getName());
        response.setProductOwnerId(project.getProductOwner() != null ? project.getProductOwner().getId() : null);
        response.setScrumMasterId(project.getScrumMaster() != null ? project.getScrumMaster().getId() : null);
        response.setDeveloperIds(
                project.getDevelopers() != null
                        ? project.getDevelopers().stream().map(Utilisateur::getId).collect(Collectors.toList())
                        : null
        );
        return response;
    }
}
