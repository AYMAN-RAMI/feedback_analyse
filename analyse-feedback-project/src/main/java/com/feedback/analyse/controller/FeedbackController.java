

package com.feedback.analyse.controller;

import com.feedback.analyse.dto.FeedbackDTO;
import com.feedback.analyse.model.Feedback;
import com.feedback.analyse.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<List<FeedbackDTO>> getAllFeedbacks() {
        List<FeedbackDTO> feedbacks = feedbackService.getAllFeedbacks()
                .stream()
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
        List<FeedbackDTO> feedbacks = feedbackService.getFeedbacksByClientId(clientId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(feedbacks);
    }

    @PostMapping("/client/{clientId}")
    public ResponseEntity<FeedbackDTO> createFeedback(@PathVariable Long clientId,
                                                      @Valid @RequestBody FeedbackDTO feedbackDTO) {

        Feedback saved = feedbackService.createFeedbackFromDTO(feedbackDTO, clientId);
        return new ResponseEntity<>(convertToDTO(saved), HttpStatus.CREATED);
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
        } else {
            dto.setClientNom("Client non défini");
        }

        return dto;
    }
}