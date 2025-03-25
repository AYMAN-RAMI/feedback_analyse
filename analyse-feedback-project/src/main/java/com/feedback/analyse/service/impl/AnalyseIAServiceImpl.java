package com.feedback.analyse.service.impl;

import com.feedback.analyse.exception.ResourceNotFoundException;
import com.feedback.analyse.model.AnalyseIA;
import com.feedback.analyse.model.Feedback;
import com.feedback.analyse.repository.AnalyseIARepository;
import com.feedback.analyse.repository.FeedbackRepository;
import com.feedback.analyse.service.AnalyseIAService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyseIAServiceImpl implements AnalyseIAService {
    private final AnalyseIARepository analyseIARepository;
    private final FeedbackRepository feedbackRepository;

    @Override
    public List<AnalyseIA> getAllAnalyses() {
        return analyseIARepository.findAll();
    }

    @Override
    public AnalyseIA getAnalyseById(Long id) {
        return analyseIARepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Analyse IA non trouvée avec l'id : " + id));
    }

    @Override
    public AnalyseIA getAnalyseByFeedbackId(Long feedbackId) {
        return analyseIARepository.findByFeedbackId(feedbackId)
                .orElseThrow(() -> new ResourceNotFoundException("Analyse IA non trouvée pour le feedback avec l'id : " + feedbackId));
    }

    @Override
    public AnalyseIA analyserFeedback(Long feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback non trouvé avec l'id : " + feedbackId));
        
        // Vérifier si une analyse existe déjà
        analyseIARepository.findByFeedbackId(feedbackId).ifPresent(analyseIARepository::delete);
        
        // Logique d'analyse du feedback (à implémenter avec une vraie IA)
        AnalyseIA analyseIA = new AnalyseIA();
        analyseIA.setFeedback(feedback);
        analyseIA.setDateAnalyse(LocalDateTime.now());
        
        // Exemple simple d'analyse basée sur le contenu
        String contenu = feedback.getContenu().toLowerCase();
        
        if (contenu.contains("excellent") || contenu.contains("parfait") || contenu.contains("bravo")) {
            analyseIA.setSentiment("positif");
            analyseIA.setScore(0.9f);
            analyseIA.setTypeDetecte("satisfaction");
            analyseIA.setRecommandation("Maintenir la qualité actuelle");
        } else if (contenu.contains("bien") || contenu.contains("satisfait")) {
            analyseIA.setSentiment("positif");
            analyseIA.setScore(0.7f);
            analyseIA.setTypeDetecte("approbation");
            analyseIA.setRecommandation("Améliorer certains aspects mineurs");
        } else if (contenu.contains("moyen") || contenu.contains("correct")) {
            analyseIA.setSentiment("neutre");
            analyseIA.setScore(0.5f);
            analyseIA.setTypeDetecte("neutralité");
            analyseIA.setRecommandation("Identifier les points d'amélioration");
        } else if (contenu.contains("problème") || contenu.contains("déçu")) {
            analyseIA.setSentiment("négatif");
            analyseIA.setScore(0.3f);
            analyseIA.setTypeDetecte("déception");
            analyseIA.setRecommandation("Résoudre les problèmes identifiés rapidement");
        } else if (contenu.contains("horrible") || contenu.contains("inacceptable")) {
            analyseIA.setSentiment("négatif");
            analyseIA.setScore(0.1f);
            analyseIA.setTypeDetecte("colère");
            analyseIA.setRecommandation("Action immédiate requise, contacter le client");
        } else {
            analyseIA.setSentiment("neutre");
            analyseIA.setScore(0.5f);
            analyseIA.setTypeDetecte("indéterminé");
            analyseIA.setRecommandation("Analyse manuelle requise");
        }
        
        // Mettre à jour le sentiment dans le feedback
        feedback.setSentiment(analyseIA.getSentiment());
        feedbackRepository.save(feedback);
        
        return analyseIARepository.save(analyseIA);
    }

    @Override
    public AnalyseIA updateAnalyse(Long id, AnalyseIA analyseDetails) {
        AnalyseIA analyseIA = getAnalyseById(id);
        
        analyseIA.setSentiment(analyseDetails.getSentiment());
        analyseIA.setScore(analyseDetails.getScore());
        analyseIA.setTypeDetecte(analyseDetails.getTypeDetecte());
        analyseIA.setRecommandation(analyseDetails.getRecommandation());
        
        return analyseIARepository.save(analyseIA);
    }

    @Override
    public void deleteAnalyse(Long id) {
        AnalyseIA analyseIA = getAnalyseById(id);
        analyseIARepository.delete(analyseIA);
    }
}
