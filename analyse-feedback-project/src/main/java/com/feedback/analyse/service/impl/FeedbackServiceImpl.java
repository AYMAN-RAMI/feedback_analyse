package com.feedback.analyse.service.impl;

import com.feedback.analyse.exception.ResourceNotFoundException;
import com.feedback.analyse.model.Client;
import com.feedback.analyse.model.Feedback;
import com.feedback.analyse.repository.ClientRepository;
import com.feedback.analyse.repository.FeedbackRepository;
import com.feedback.analyse.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final ClientRepository clientRepository;

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback non trouvé avec l'id : " + id));
    }

    @Override
    public List<Feedback> getFeedbacksByClientId(Long clientId) {
        return feedbackRepository.findByClientId(clientId);
    }

    @Override
    public List<Feedback> getFeedbacksByCategorie(String categorie) {
        return feedbackRepository.findByCategorie(categorie);
    }

    @Override
    public List<Feedback> getFeedbacksBySentiment(String sentiment) {
        return feedbackRepository.findBySentiment(sentiment);
    }

    @Override
    public List<Feedback> getFeedbacksByDateRange(LocalDateTime debut, LocalDateTime fin) {
        return feedbackRepository.findByDateSoumissionBetween(debut, fin);
    }

    @Override
    public Feedback createFeedback(Feedback feedback, Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client non trouvé avec l'id : " + clientId));
        
        feedback.setClient(client);
        feedback.setDateSoumission(LocalDateTime.now());
        
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback updateFeedback(Long id, Feedback feedbackDetails) {
        Feedback feedback = getFeedbackById(id);
        
        feedback.setContenu(feedbackDetails.getContenu());
        feedback.setCategorie(feedbackDetails.getCategorie());
        feedback.setSentiment(feedbackDetails.getSentiment());
        
        return feedbackRepository.save(feedback);
    }

    @Override
    public void deleteFeedback(Long id) {
        Feedback feedback = getFeedbackById(id);
        feedbackRepository.delete(feedback);
    }
}
