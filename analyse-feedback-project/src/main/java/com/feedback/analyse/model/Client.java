package com.feedback.analyse.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.List;

@Entity
@Table(name = "clients")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Client extends Utilisateur {
    @Column(nullable = false)
    private String entreprise;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks;
}
