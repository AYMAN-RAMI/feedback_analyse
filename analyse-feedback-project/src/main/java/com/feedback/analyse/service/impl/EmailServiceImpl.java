package com.feedback.analyse.service.impl;

import com.feedback.analyse.model.FeedbackRequest;
import com.feedback.analyse.model.Utilisateur;
import com.feedback.analyse.repository.FeedbackRequestRepository;
import com.feedback.analyse.repository.UtilisateurRepository;
import com.feedback.analyse.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final FeedbackRequestRepository feedbackRequestRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public EmailServiceImpl(JavaMailSender mailSender, FeedbackRequestRepository feedbackRequestRepository, UtilisateurRepository utilisateurRepository) {
        this.mailSender = mailSender;
        this.feedbackRequestRepository = feedbackRequestRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public void sendFeedbackRequestEmail(Long poId, Long clientId) {
        Utilisateur po = utilisateurRepository.findById(poId)
                .orElseThrow(() -> new RuntimeException("Product Owner introuvable"));
        Utilisateur client = utilisateurRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client introuvable"));

        String subject = "Demande de Feedback - " + po.getNom();

        // 🔥 Version améliorée du message
        String feedbackFormLink = "http://localhost:4200/feedback-form?clientId=" + clientId + "&poId=" + poId;
        String body = "Bonjour " + client.getNom() + ",\n\n"
                + "Nous vous remercions de prendre un moment pour partager votre retour d'expérience.\n\n"
                + "Veuillez remplir ce court formulaire en cliquant sur le lien suivant :\n\n"
                + feedbackFormLink + "\n\n"
                + "Merci beaucoup pour votre collaboration !\n\n"
                + "Cordialement,\n"
                + "L'équipe Projet.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(client.getEmail());
        message.setSubject(subject);
        message.setText(body);

        // 💌 Envoi du mail
        mailSender.send(message);

        // 📋 Enregistrement de la demande de feedback
        FeedbackRequest feedbackRequest = new FeedbackRequest();
        feedbackRequest.setPo(po);
        feedbackRequest.setClient(client);
        feedbackRequest.setRequestDate(LocalDate.now());
        feedbackRequestRepository.save(feedbackRequest);
    }
}
