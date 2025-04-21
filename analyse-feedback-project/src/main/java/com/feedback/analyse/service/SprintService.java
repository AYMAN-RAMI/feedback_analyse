package com.feedback.analyse.service;

import com.feedback.analyse.model.Sprint;
import java.time.LocalDateTime;
import java.util.List;
import com.feedback.analyse.dto.SprintDTO;


public interface SprintService {
    List<SprintDTO> getAllSprintDTOs();
    List<Sprint> getAllSprints();
    Sprint getSprintById(Long id);
    List<Sprint> getSprintsActifs(LocalDateTime date);
    Sprint createSprint(Sprint sprint);
    Sprint updateSprint(Long id, Sprint sprint);
    Sprint demarrerSprint(Long id, LocalDateTime dateDebut);
    Sprint terminerSprint(Long id, LocalDateTime dateFin);
    void deleteSprint(Long id);
}
