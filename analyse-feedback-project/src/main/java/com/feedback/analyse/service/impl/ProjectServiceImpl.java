package com.feedback.analyse.service.impl;



import com.feedback.analyse.dto.ProjectDTO;
import com.feedback.analyse.model.Project;
import com.feedback.analyse.model.Utilisateur;
import com.feedback.analyse.repository.ProjectRepository;
import com.feedback.analyse.repository.UtilisateurRepository;
import com.feedback.analyse.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public Project createProject(ProjectDTO dto) {
        Project project = new Project();
        project.setName(dto.getName());

        Utilisateur productOwner = utilisateurRepository.findById(dto.getProductOwnerId())
                .orElseThrow(() -> new RuntimeException("Product Owner not found"));
        Utilisateur scrumMaster = utilisateurRepository.findById(dto.getScrumMasterId())
                .orElseThrow(() -> new RuntimeException("Scrum Master not found"));
        List<Utilisateur> developers = utilisateurRepository.findAllById(dto.getDeveloperIds());

        project.setProductOwner(productOwner);
        project.setScrumMaster(scrumMaster);
        project.setDevelopers(developers);

        return projectRepository.save(project);
    }
    @Override
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project updateProject(Long id, ProjectDTO dto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setName(dto.getName());
        project.setProductOwner(utilisateurRepository.findById(dto.getProductOwnerId())
                .orElseThrow(() -> new RuntimeException("Product Owner not found")));
        project.setScrumMaster(utilisateurRepository.findById(dto.getScrumMasterId())
                .orElseThrow(() -> new RuntimeException("Scrum Master not found")));
        project.setDevelopers(utilisateurRepository.findAllById(dto.getDeveloperIds()));

        return projectRepository.save(project);
    }

    @Override
    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new RuntimeException("Project not found");
        }
        projectRepository.deleteById(id);
    }
}
