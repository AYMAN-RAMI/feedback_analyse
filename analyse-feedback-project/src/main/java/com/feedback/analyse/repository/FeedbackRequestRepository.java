package com.feedback.analyse.repository;

import com.feedback.analyse.model.FeedbackRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedbackRequestRepository extends JpaRepository<FeedbackRequest, Long> {

    Optional<FeedbackRequest> findByClientId(Long clientId);
}
