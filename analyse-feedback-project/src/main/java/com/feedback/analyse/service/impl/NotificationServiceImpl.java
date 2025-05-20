package com.feedback.analyse.service.impl;

import com.feedback.analyse.model.Notification;
import com.feedback.analyse.model.FeedbackRequest;
import com.feedback.analyse.model.Utilisateur;
import com.feedback.analyse.repository.NotificationRepository;
import com.feedback.analyse.repository.FeedbackRequestRepository;
import com.feedback.analyse.service.NotificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final FeedbackRequestRepository feedbackRequestRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository, FeedbackRequestRepository feedbackRequestRepository) {
        this.notificationRepository = notificationRepository;
        this.feedbackRequestRepository = feedbackRequestRepository;
    }

    @Override
    public void notifyPo(Long clientId) {
        FeedbackRequest feedbackRequest = feedbackRequestRepository.findByClientId(clientId)
                .orElseThrow(() -> new RuntimeException("Demande de feedback non trouvée pour ce client."));

        Utilisateur po = feedbackRequest.getPo();

        Notification notification = new Notification();
        notification.setContenu("Le client " + feedbackRequest.getClient().getNom() + " a soumis un feedback.");
        notification.setDateEnvoi(LocalDate.now());
        notification.setDestinataire(po);

        notificationRepository.save(notification);
    }
}
