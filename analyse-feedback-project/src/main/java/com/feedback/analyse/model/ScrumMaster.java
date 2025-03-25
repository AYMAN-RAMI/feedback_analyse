package com.feedback.analyse.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "scrum_masters")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ScrumMaster extends Utilisateur {
    // Attributs spécifiques au ScrumMaster si nécessaire
}
