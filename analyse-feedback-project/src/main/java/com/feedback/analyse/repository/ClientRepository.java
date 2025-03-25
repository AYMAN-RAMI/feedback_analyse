package com.feedback.analyse.repository;

import com.feedback.analyse.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // Méthodes spécifiques si nécessaire
}
