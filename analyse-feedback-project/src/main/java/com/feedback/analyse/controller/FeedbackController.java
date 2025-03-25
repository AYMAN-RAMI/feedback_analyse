package com.feedback.analyse.controller;

import com.feedback.analyse.dto.FeedbackDTO;
import com.feedback.analyse.model.Feedback;
import com.feedback.analyse.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<List<FeedbackDTO>> getAllFeedbacks() {
        List<FeedbackDTO> feedbacks = feedbackService.getAllFeedbacks().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackDTO> getFeedbackById(@PathVariable Long id) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        return ResponseEntity.ok(convertToDTO(feedback));
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<FeedbackDTO>> getFeedbacksByClientId(@PathVariable Long clientId) {
        List<FeedbackDTO> feedbacks = feedbackService.getFeedbacksByClientId(clientId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/categorie/{categorie}")
    public ResponseEntity<List<FeedbackDTO>> getFeedbacksByCategorie(@PathVariable String categorie) {
        List<FeedbackDTO> feedbacks = feedbackService.getFeedbacksByCategorie(categorie).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/sentiment/{sentiment}")
    public ResponseEntity<List<FeedbackDTO>> getFeedbacksBySentiment(@PathVariable String sentiment) {
        List<FeedbackDTO> feedbacks = feedbackService.getFeedbacksBySentiment(sentiment).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/date")
    public ResponseEntity<List<FeedbackDTO>> getFeedbacksByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime debut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        List<FeedbackDTO> feedbacks = feedbackService.getFeedbacksByDateRange(debut, fin).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(feedbacks);
    }

    @PostMapping("/client/{clientId}")
    public ResponseEntity<FeedbackDTO> createFeedback(
            @PathVariable Long clientId,
            @Valid @RequestBody FeedbackDTO feedbackDTO) {
        Feedback feedback = convertToEntity(feedbackDTO);
        Feedback newFeedback = feedbackService.createFeedback(feedback, clientId);
        return new ResponseEntity<>(convertToDTO(newFeedback), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackDTO> updateFeedback(
            @PathVariable Long id,
            @Valid @RequestBody FeedbackDTO feedbackDTO) {
        Feedback feedback = convertToEntity(feedbackDTO);
        Feedback updatedFeedback = feedbackService.updateFeedback(id, feedback);
        return ResponseEntity.ok(convertToDTO(updatedFeedback));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }

    private FeedbackDTO convertToDTO(Feedback feedback) {
        FeedbackDTO dto = new FeedbackDTO();
        dto.setId(feedback.getId());
        dto.setContenu(feedback.getContenu());
        dto.setDateSoumission(feedback.getDateSoumission());
        dto.setCategorie(feedback.getCategorie());
        dto.setSentiment(feedback.getSentiment());

        if (feedback.getClient() != null) {
            dto.setClientId(feedback.getClient().getId());
            dto.setClientNom(feedback.getClient().getNom());
        }

        return dto;
    }

    private Feedback convertToEntity(FeedbackDTO dto) {
        Feedback feedback = new Feedback();
        feedback.setId(dto.getId());
        feedback.setContenu(dto.getContenu());
        feedback.setDateSoumission(dto.getDateSoumission());
        feedback.setCategorie(dto.getCategorie());
        feedback.setSentiment(dto.getSentiment());

        return feedback;
    }
}