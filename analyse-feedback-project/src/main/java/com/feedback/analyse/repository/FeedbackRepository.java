package com.feedback.analyse.repository;

import com.feedback.analyse.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByClientId(Long clientId);
    List<Feedback> findByCategorie(String categorie);
    List<Feedback> findBySentiment(String sentiment);
    List<Feedback> findByDateSoumissionBetween(LocalDateTime debut, LocalDateTime fin);
}