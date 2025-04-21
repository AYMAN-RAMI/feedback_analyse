package com.feedback.analyse.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class

Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Utilisateur productOwner;

    @ManyToOne
    private Utilisateur scrumMaster;

    @ManyToMany
    private List<Utilisateur> developers;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Utilisateur getProductOwner() {
        return productOwner;
    }

    public void setProductOwner(Utilisateur productOwner) {
        this.productOwner = productOwner;
    }

    public Utilisateur getScrumMaster() {
        return scrumMaster;
    }

    public void setScrumMaster(Utilisateur scrumMaster) {
        this.scrumMaster = scrumMaster;
    }

    public List<Utilisateur> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Utilisateur> developers) {
        this.developers = developers;
    }
}
