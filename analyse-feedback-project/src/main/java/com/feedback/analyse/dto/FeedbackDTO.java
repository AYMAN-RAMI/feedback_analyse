package com.feedback.analyse.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDTO {
    private Long id;

    @NotBlank(message = "Le contenu est obligatoire")
    @Size(min = 10, message = "Le contenu doit contenir au moins 10 caractères")
    private String contenu;

    private LocalDateTime dateSoumission;

    private String categorie;

    private String sentiment;

    private Long clientId;

    private String clientNom;
}