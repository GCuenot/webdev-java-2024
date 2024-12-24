package fr.isen.ticketapp.interfaces.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ticket")  // Table correspondant à l'entité dans la BDD
public class Ticket {

    @Id
    private String id;

    @Column(name = "titre")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "date_creation")
    private LocalDateTime createdAt;

    @Column(name = "date_mise_a_jour")
    private LocalDateTime updatedAt;

    @Column(name = "impact")
    private String impact;

    @Column(name = "etat")
    private String state;

    @Column(name = "utilisateur_createur")
    private String createdBy;

    @Column(name = "poste_informatique")
    private String computerStation;

    @Column(name = "type_demande")
    private String requestType;

    // Génère un UUID pour l'ID avant l'insertion
    @PrePersist
    private void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }

    // Accesseurs et modificateurs
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getComputerStation() {
        return computerStation;
    }

    public void setComputerStation(String computerStation) {
        this.computerStation = computerStation;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    // Affichage simplifié de l'objet
    @Override
    public String toString() {
        return String.format("Ticket{id='%s', title='%s', description='%s', createdAt=%s, updatedAt=%s, impact='%s', state='%s', createdBy='%s', computerStation='%s', requestType='%s'}", 
                             id, title, description, createdAt, updatedAt, impact, state, createdBy, computerStation, requestType);
    }
}
