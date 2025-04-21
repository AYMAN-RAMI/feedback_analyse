package com.feedback.analyse.controller;

import com.feedback.analyse.dto.SprintDTO;
import com.feedback.analyse.model.Sprint;
import com.feedback.analyse.model.Ticket;
import com.feedback.analyse.service.SprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sprints")
@RequiredArgsConstructor
public class SprintController {
    private final SprintService sprintService;

    @GetMapping
    public ResponseEntity<List<SprintDTO>> getAllSprints() {
        List<SprintDTO> sprints = sprintService.getAllSprints().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(sprints);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SprintDTO> getSprintById(@PathVariable Long id) {
        Sprint sprint = sprintService.getSprintById(id);
        return ResponseEntity.ok(convertToDTO(sprint));
    }

    @GetMapping("/actifs")
    public ResponseEntity<List<SprintDTO>> getSprintsActifs(@RequestParam(required = false) LocalDateTime date) {
        if (date == null) {
            date = LocalDateTime.now();
        }
        List<SprintDTO> sprints = sprintService.getSprintsActifs(date).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(sprints);
    }

    @PostMapping
    public ResponseEntity<SprintDTO> createSprint(@RequestBody SprintDTO sprintDTO) {
        Sprint sprint = convertToEntity(sprintDTO);
        Sprint newSprint = sprintService.createSprint(sprint);
        return new ResponseEntity<>(convertToDTO(newSprint), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SprintDTO> updateSprint(
            @PathVariable Long id,
            @RequestBody SprintDTO sprintDTO) {
        Sprint sprint = convertToEntity(sprintDTO);
        Sprint updatedSprint = sprintService.updateSprint(id, sprint);
        return ResponseEntity.ok(convertToDTO(updatedSprint));
    }

    @PutMapping("/{id}/demarrer")
    public ResponseEntity<SprintDTO> demarrerSprint(
            @PathVariable Long id,
            @RequestParam(required = false) LocalDateTime dateDebut) {
        if (dateDebut == null) {
            dateDebut = LocalDateTime.now();
        }
        Sprint updatedSprint = sprintService.demarrerSprint(id, dateDebut);
        return ResponseEntity.ok(convertToDTO(updatedSprint));
    }

    @PutMapping("/{id}/terminer")
    public ResponseEntity<SprintDTO> terminerSprint(
            @PathVariable Long id,
            @RequestParam(required = false) LocalDateTime dateFin) {
        if (dateFin == null) {
            dateFin = LocalDateTime.now();
        }
        Sprint updatedSprint = sprintService.terminerSprint(id, dateFin);
        return ResponseEntity.ok(convertToDTO(updatedSprint));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSprint(@PathVariable Long id) {
        sprintService.deleteSprint(id);
        return ResponseEntity.noContent().build();
    }

    // === Conversion helpers ===

    private SprintDTO convertToDTO(Sprint sprint) {
        SprintDTO dto = new SprintDTO();
        dto.setId(sprint.getId());
        dto.setDateDebut(sprint.getDateDebut());
        dto.setDateFin(sprint.getDateFin());
        dto.setStatut(sprint.getStatut());

        // Ajout de ticketId (relation ManyToOne)
        if (sprint.getTicket() != null) {
            dto.setTicketId(sprint.getTicket().getId());
        }

        return dto;
    }

    private Sprint convertToEntity(SprintDTO dto) {
        Sprint sprint = new Sprint();
        sprint.setId(dto.getId());
        sprint.setDateDebut(dto.getDateDebut());
        sprint.setDateFin(dto.getDateFin());
        sprint.setStatut(dto.getStatut());

        // Ajout de la liaison ticket via ticketId
        if (dto.getTicketId() != null) {
            Ticket ticket = new Ticket();
            ticket.setId(dto.getTicketId());
            sprint.setTicket(ticket);
        }

        return sprint;
    }
}
