package com.feedback.analyse.controller;

import com.feedback.analyse.dto.TicketDTO;
import com.feedback.analyse.model.Ticket;
import com.feedback.analyse.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        List<TicketDTO> tickets = ticketService.getAllTickets().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable Long id) {
        Ticket ticket = ticketService.getTicketById(id);
        return ResponseEntity.ok(convertToDTO(ticket));
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<TicketDTO>> getTicketsByStatut(@PathVariable String statut) {
        List<TicketDTO> tickets = ticketService.getTicketsByStatut(statut).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/priorite/{priorite}")
    public ResponseEntity<List<TicketDTO>> getTicketsByPriorite(@PathVariable String priorite) {
        List<TicketDTO> tickets = ticketService.getTicketsByPriorite(priorite).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tickets);
    }

    @PostMapping
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO) {
        Ticket ticket = convertToEntity(ticketDTO);
        Ticket newTicket = ticketService.createTicket(ticket);
        return new ResponseEntity<>(convertToDTO(newTicket), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDTO> updateTicket(
            @PathVariable Long id,
            @RequestBody TicketDTO ticketDTO) {
        Ticket ticket = convertToEntity(ticketDTO);
        Ticket updatedTicket = ticketService.updateTicket(id, ticket);
        return ResponseEntity.ok(convertToDTO(updatedTicket));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }

    private TicketDTO convertToDTO(Ticket ticket) {
        TicketDTO dto = new TicketDTO();
        dto.setId(ticket.getId());
        dto.setTitre(ticket.getTitre());
        dto.setDescription(ticket.getDescription());
        dto.setStatut(ticket.getStatut());

        // Retirez les parties qui utilisent getBacklog(), getSprint(), et getAssignee()
        // car ces méthodes ne sont pas définies dans la classe Ticket.

        return dto;
    }

    private Ticket convertToEntity(TicketDTO dto) {
        Ticket ticket = new Ticket();
        ticket.setId(dto.getId());
        ticket.setTitre(dto.getTitre());
        ticket.setDescription(dto.getDescription());
        ticket.setStatut(dto.getStatut());
        return ticket;
    }
}