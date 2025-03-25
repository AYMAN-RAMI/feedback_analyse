package com.feedback.analyse.repository;

import com.feedback.analyse.model.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {
    List<Sprint> findByDateDebutBefore(LocalDateTime date);
    List<Sprint> findByDateFinAfter(LocalDateTime date);
    List<Sprint> findByDateDebutBeforeAndDateFinAfter(LocalDateTime dateDebut, LocalDateTime dateFin);
}
