package com.feedback.analyse.service.impl;

import com.feedback.analyse.dto.SprintDTO;
import com.feedback.analyse.exception.ResourceNotFoundException;
import com.feedback.analyse.model.Sprint;
import com.feedback.analyse.repository.SprintRepository;
import com.feedback.analyse.service.SprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SprintServiceImpl implements SprintService {

    private final SprintRepository sprintRepository;

    @Override
    public List<Sprint> getAllSprints() {
        return sprintRepository.findAll();
    }

    @Override
    public Sprint getSprintById(Long id) {
        return sprintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sprint non trouvé avec l'id : " + id));
    }

    @Override
    public List<Sprint> getSprintsActifs(LocalDateTime date) {
        return sprintRepository.findByDateDebutBeforeAndDateFinAfter(date, date);
    }

    @Override
    public Sprint createSprint(Sprint sprint) {
        return sprintRepository.save(sprint);
    }

    @Override
    public Sprint updateSprint(Long id, Sprint sprintDetails) {
        Sprint sprint = getSprintById(id);

        sprint.setDateDebut(sprintDetails.getDateDebut());
        sprint.setDateFin(sprintDetails.getDateFin());
        sprint.setStatut(sprintDetails.getStatut());

        return sprintRepository.save(sprint);
    }

    @Override
    public Sprint demarrerSprint(Long id, LocalDateTime dateDebut) {
        Sprint sprint = getSprintById(id);
        sprint.setDateDebut(dateDebut);
        return sprintRepository.save(sprint);
    }

    @Override
    public Sprint terminerSprint(Long id, LocalDateTime dateFin) {
        Sprint sprint = getSprintById(id);
        sprint.setDateFin(dateFin);
        return sprintRepository.save(sprint);
    }

    @Override
    public void deleteSprint(Long id) {
        Sprint sprint = getSprintById(id);
        sprintRepository.delete(sprint);
    }

    // ✅ Méthode pour convertir un Sprint en SprintDTO
    public SprintDTO convertToDTO(Sprint sprint) {
        SprintDTO dto = new SprintDTO();
        dto.setId(sprint.getId());
        dto.setDateDebut(sprint.getDateDebut());
        dto.setDateFin(sprint.getDateFin());
        dto.setStatut(sprint.getStatut());

        if (sprint.getTickets() != null) {
            dto.setTicketIds(
                    sprint.getTickets().stream()
                            .map(ticket -> ticket.getId())
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    // ✅ Méthode pour retourner tous les Sprints au format DTO
    @Override
    public List<SprintDTO> getAllSprintDTOs() {
        return sprintRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
