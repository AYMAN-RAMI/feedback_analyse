package com.feedback.analyse.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "administrateurs")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Administrateur extends Utilisateur {
    // Attributs spécifiques à l'administrateur si nécessaire
}
