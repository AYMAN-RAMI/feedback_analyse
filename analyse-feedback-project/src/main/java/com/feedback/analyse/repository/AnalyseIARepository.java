package com.feedback.analyse.repository;

import com.feedback.analyse.model.AnalyseIA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AnalyseIARepository extends JpaRepository<AnalyseIA, Long> {
    Optional<AnalyseIA> findByFeedbackId(Long feedbackId);
}
