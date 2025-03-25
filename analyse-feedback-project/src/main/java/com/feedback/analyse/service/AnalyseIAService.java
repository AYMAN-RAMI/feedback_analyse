package com.feedback.analyse.service;

import com.feedback.analyse.model.AnalyseIA;
import java.util.List;

public interface AnalyseIAService {
    List<AnalyseIA> getAllAnalyses();
    AnalyseIA getAnalyseById(Long id);
    AnalyseIA getAnalyseByFeedbackId(Long feedbackId);
    AnalyseIA analyserFeedback(Long feedbackId);
    AnalyseIA updateAnalyse(Long id, AnalyseIA analyseIA);
    void deleteAnalyse(Long id);
}
