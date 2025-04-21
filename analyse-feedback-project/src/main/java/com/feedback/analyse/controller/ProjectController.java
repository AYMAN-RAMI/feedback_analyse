package com.feedback.analyse.controller;


import com.feedback.analyse.exception.ResourceNotFoundException;
import com.feedback.analyse.dto.ProjectDTO;
import com.feedback.analyse.model.Project;
import com.feedback.analyse.model.Utilisateur;
import com.feedback.analyse.repository.ProjectRepository;
import com.feedback.analyse.repository.UtilisateurRepository;
import com.feedback.analyse.dto.ProjectResponse;

import com.feedback.analyse.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/create")

    public ResponseEntity<?> createProject(@RequestBody ProjectDTO dto) {
        projectService.createProject(dto);
        return ResponseEntity.ok("Projet créé avec succès");
    }

    @GetMapping("/all")

    public ResponseEntity<List<Project>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @PutMapping("/update/{id}")

    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody ProjectDTO dto) {
        return ResponseEntity.ok(projectService.updateProject(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok("Projet supprimé avec succès");
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id) {
        Project project = projectService.getProjectById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        ProjectResponse response = new ProjectResponse();
        response.setId(project.getId());
        response.setName(project.getName());
        response.setProductOwnerId(project.getProductOwner().getId());
        response.setScrumMasterId(project.getScrumMaster().getId());
        response.setDeveloperIds(project.getDevelopers().stream()
                .map(Utilisateur::getId)
                .collect(Collectors.toList()));

        return ResponseEntity.ok(response);
    }
}

