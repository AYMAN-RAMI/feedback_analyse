package com.feedback.analyse.repository;

import com.feedback.analyse.model.Backlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends JpaRepository<Backlog, Long> {
    // Méthodes spécifiques si nécessaire
}
