package com.feedback.analyse.service;

import com.feedback.analyse.model.Ticket;
import java.util.List;

public interface TicketService {
    List<Ticket> getAllTickets();
    Ticket getTicketById(Long id);
    List<Ticket> getTicketsByStatut(String statut);
    List<Ticket> getTicketsByPriorite(String priorite);
    List<Ticket> getTicketsByAssigneeId(Long assigneeId);
    List<Ticket> getTicketsByBacklogId(Long backlogId);
    List<Ticket> getTicketsBySprintId(Long sprintId);
    Ticket createTicket(Ticket ticket);
    Ticket updateTicket(Long id, Ticket ticket);
    Ticket assignerTicket(Long id, Long utilisateurId);
    Ticket changerStatut(Long id, String nouveauStatut);
    void deleteTicket(Long id);
}
