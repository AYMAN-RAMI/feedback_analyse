package com.feedback.analyse.service;

import com.feedback.analyse.model.Feedback;
import java.time.LocalDateTime;
import java.util.List;

public interface FeedbackService {
    List<Feedback> getAllFeedbacks();
    Feedback getFeedbackById(Long id);
    List<Feedback> getFeedbacksByClientId(Long clientId);
    List<Feedback> getFeedbacksByCategorie(String categorie);
    List<Feedback> getFeedbacksBySentiment(String sentiment);
    List<Feedback> getFeedbacksByDateRange(LocalDateTime debut, LocalDateTime fin);
    Feedback createFeedback(Feedback feedback, Long clientId);
    Feedback updateFeedback(Long id, Feedback feedback);
    void deleteFeedback(Long id);
}
