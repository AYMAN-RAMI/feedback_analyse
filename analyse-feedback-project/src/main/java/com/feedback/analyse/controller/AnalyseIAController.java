package com.feedback.analyse.controller;

import com.feedback.analyse.dto.AnalyseIADTO;
import com.feedback.analyse.model.AnalyseIA;
import com.feedback.analyse.service.AnalyseIAService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/analyses")
@RequiredArgsConstructor
public class AnalyseIAController {
    private final AnalyseIAService analyseIAService;
    
    @GetMapping
    public ResponseEntity<List<AnalyseIADTO>> getAllAnalyses() {
        List<AnalyseIADTO> analyses = analyseIAService.getAllAnalyses().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(analyses);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AnalyseIADTO> getAnalyseById(@PathVariable Long id) {
        AnalyseIA analyseIA = analyseIAService.getAnalyseById(id);
        return ResponseEntity.ok(convertToDTO(analyseIA));
    }
    
    @GetMapping("/feedback/{feedbackId}")
    public ResponseEntity<AnalyseIADTO> getAnalyseByFeedbackId(@PathVariable Long feedbackId) {
        AnalyseIA analyseIA = analyseIAService.getAnalyseByFeedbackId(feedbackId);
        return ResponseEntity.ok(convertToDTO(analyseIA));
    }
    
    @PostMapping("/feedback/{feedbackId}")
    public ResponseEntity<AnalyseIADTO> analyserFeedback(@PathVariable Long feedbackId) {
        AnalyseIA analyseIA = analyseIAService.analyserFeedback(feedbackId);
        return new ResponseEntity<>(convertToDTO(analyseIA), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AnalyseIADTO> updateAnalyse(
            @PathVariable Long id, 
            @RequestBody AnalyseIADTO analyseIADTO) {
        AnalyseIA analyseIA = convertToEntity(analyseIADTO);
        AnalyseIA updatedAnalyseIA = analyseIAService.updateAnalyse(id, analyseIA);
        return ResponseEntity.ok(convertToDTO(updatedAnalyseIA));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnalyse(@PathVariable Long id) {
        analyseIAService.deleteAnalyse(id);
        return ResponseEntity.noContent().build();
    }
    
    private AnalyseIADTO convertToDTO(AnalyseIA analyseIA) {
        AnalyseIADTO dto = new AnalyseIADTO();
        dto.setId(analyseIA.getId());
        dto.setFeedbackId(analyseIA.getFeedback().getId());
        dto.setSentiment(analyseIA.getSentiment());
        dto.setScore(analyseIA.getScore());
        dto.setTypeDetecte(analyseIA.getTypeDetecte());
        dto.setRecommandation(analyseIA.getRecommandation());
        dto.setDateAnalyse(analyseIA.getDateAnalyse());
        return dto;
    }
    
    private AnalyseIA convertToEntity(AnalyseIADTO dto) {
        AnalyseIA analyseIA = new AnalyseIA();
        analyseIA.setId(dto.getId());
        analyseIA.setSentiment(dto.getSentiment());
        analyseIA.setScore(dto.getScore());
        analyseIA.setTypeDetecte(dto.getTypeDetecte());
        analyseIA.setRecommandation(dto.getRecommandation());
        analyseIA.setDateAnalyse(dto.getDateAnalyse());
        return analyseIA;
    }
}
