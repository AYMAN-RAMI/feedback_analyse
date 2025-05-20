package com.feedback.analyse.service;

public interface EmailService {
    void sendFeedbackRequestEmail(Long poId, Long clientId);
}