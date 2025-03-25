package com.feedback.analyse.service.impl;

import com.feedback.analyse.exception.ResourceNotFoundException;
import com.feedback.analyse.model.Ticket;
import com.feedback.analyse.model.Utilisateur;
import com.feedback.analyse.repository.TicketRepository;
import com.feedback.analyse.repository.UtilisateurRepository;
import com.feedback.analyse.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket non trouvé avec l'id : " + id));
    }

    @Override
    public List<Ticket> getTicketsByStatut(String statut) {
        return ticketRepository.findByStatut(statut);
    }

    @Override
    public List<Ticket> getTicketsByPriorite(String priorite) {
        return ticketRepository.findByPriorite(priorite);
    }

    @Override
    public List<Ticket> getTicketsByAssigneeId(Long assigneeId) {
        return ticketRepository.findByAssigneeId(assigneeId);
    }

    @Override
    public List<Ticket> getTicketsByBacklogId(Long backlogId) {
        return ticketRepository.findByBacklogId(backlogId);
    }

    @Override
    public List<Ticket> getTicketsBySprintId(Long sprintId) {
        return ticketRepository.findBySprintId(sprintId);
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket updateTicket(Long id, Ticket ticketDetails) {
        Ticket ticket = getTicketById(id);
        
        ticket.setTitre(ticketDetails.getTitre());
        ticket.setDescription(ticketDetails.getDescription());
        ticket.setStatut(ticketDetails.getStatut());
        ticket.setPriorite(ticketDetails.getPriorite());
        ticket.setBacklog(ticketDetails.getBacklog());
        ticket.setSprint(ticketDetails.getSprint());
        
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket assignerTicket(Long id, Long utilisateurId) {
        Ticket ticket = getTicketById(id);
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'id : " + utilisateurId));
        
        ticket.setAssignee(utilisateur);
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket changerStatut(Long id, String nouveauStatut) {
        Ticket ticket = getTicketById(id);
        ticket.setStatut(nouveauStatut);
        return ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(Long id) {
        Ticket ticket = getTicketById(id);
        ticketRepository.delete(ticket);
    }
}
