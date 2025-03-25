package com.feedback.analyse.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "product_owners")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductOwner extends Utilisateur {
    // Attributs spécifiques au ProductOwner si nécessaire
}
