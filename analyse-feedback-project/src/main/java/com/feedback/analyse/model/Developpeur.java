package com.feedback.analyse.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "developpeurs")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Developpeur extends Utilisateur {
    // Attributs spécifiques au Développeur si nécessaire
}
