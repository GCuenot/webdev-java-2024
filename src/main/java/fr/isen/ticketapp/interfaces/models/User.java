package fr.isen.ticketapp.interfaces.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")  // Table correspondante en BDD
public class User {

    @Id
    private String id;

    @Column(name = "nom")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "mot_de_passe")
    private String password;

    @Column(name = "date_derniere_connexion")
    private LocalDateTime lastLogin;

    @Column(name = "statut")
    private String status;

    @Column(name = "role")
    private String role;

    // Génère un ID unique avant la persistance
    @PrePersist
    private void generateId() {
        if (this.id == null) {
            this.id = "U" + String.format("%03d", (int) (Math.random() * 1000));  // ID unique basé sur un nombre aléatoire
        }
    }

    // Accesseurs et modificateurs
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Affichage de l'objet sous forme de chaîne
    @Override
    public String toString() {
        return String.format("User{id='%s', name='%s', email='%s', password='%s', lastLogin=%s, status='%s', role='%s'}",
                             id, name, email, password, lastLogin, status, role);
    }
}
