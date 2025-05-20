

package com.feedback.analyse.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // 🚨 NE SERA PAS PRÉSENT DANS LE POST

    private Long id;

    @NotBlank(message = "Le contenu est obligatoire")
    @Size(min = 10, message = "Le contenu doit contenir au moins 10 caractères")
    private String contenu;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dateSoumission;

    private String categorie;
    private String sentiment;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // 🚨 NE SERA PAS PRÉSENT DANS LE POST

    private Long clientId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String clientNom;
}