package com.feedback.analyse.repository;

import com.feedback.analyse.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByStatut(String statut);
    List<Ticket> findByPriorite(String priorite);
    List<Ticket> findByAssigneeId(Long assigneeId);
    List<Ticket> findByBacklogId(Long backlogId);
    List<Ticket> findBySprintId(Long sprintId);
}
