package com.feedback.analyse.repository;

import com.feedback.analyse.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    @Query("SELECT f FROM Feedback f JOIN FETCH f.client WHERE f.client.id = :clientId")
    List<Feedback> findByClientIdWithClient(@Param("clientId") Long clientId);

    List<Feedback> findByCategorie(String categorie);

    List<Feedback> findBySentiment(String sentiment);

    List<Feedback> findByDateSoumissionBetween(LocalDateTime debut, LocalDateTime fin);
}
