package com.feedback.analyse.controller;

import com.feedback.analyse.dto.BacklogDTO;
import com.feedback.analyse.model.Backlog;
import com.feedback.analyse.service.BacklogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/backlogs")
@RequiredArgsConstructor
public class BacklogController {
    private final BacklogService backlogService;
    
    @GetMapping
    public ResponseEntity<List<BacklogDTO>> getAllBacklogs() {
        List<BacklogDTO> backlogs = backlogService.getAllBacklogs().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(backlogs);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<BacklogDTO> getBacklogById(@PathVariable Long id) {
        Backlog backlog = backlogService.getBacklogById(id);
        return ResponseEntity.ok(convertToDTO(backlog));
    }
    
    @PostMapping
    public ResponseEntity<BacklogDTO> createBacklog(@RequestBody BacklogDTO backlogDTO) {
        Backlog backlog = convertToEntity(backlogDTO);
        Backlog newBacklog = backlogService.createBacklog(backlog);
        return new ResponseEntity<>(convertToDTO(newBacklog), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<BacklogDTO> updateBacklog(
            @PathVariable Long id, 
            @RequestBody BacklogDTO backlogDTO) {
        Backlog backlog = convertToEntity(backlogDTO);
        Backlog updatedBacklog = backlogService.updateBacklog(id, backlog);
        return ResponseEntity.ok(convertToDTO(updatedBacklog));
    }
    
    @PutMapping("/{id}/statut/{statut}")
    public ResponseEntity<BacklogDTO> updateStatut(
            @PathVariable Long id, 
            @PathVariable String statut) {
        Backlog updatedBacklog = backlogService.updateStatut(id, statut);
        return ResponseEntity.ok(convertToDTO(updatedBacklog));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBacklog(@PathVariable Long id) {
        backlogService.deleteBacklog(id);
        return ResponseEntity.noContent().build();
    }
    
    private BacklogDTO convertToDTO(Backlog backlog) {
        BacklogDTO dto = new BacklogDTO();
        dto.setId(backlog.getId());
        dto.setStatut(backlog.getStatut());
        
        if (backlog.getTickets() != null) {
            dto.setTicketIds(backlog.getTickets().stream()
                    .map(ticket -> ticket.getId())
                    .collect(Collectors.toList()));
        }
        
        return dto;
    }
    
    private Backlog convertToEntity(BacklogDTO dto) {
        Backlog backlog = new Backlog();
        backlog.setId(dto.getId());
        backlog.setStatut(dto.getStatut());
        return backlog;
    }
}
