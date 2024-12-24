package fr.isen.ticketapp.interfaces.models;

import jakarta.persistence.*;

@Entity
@Table(name = "device")  // Table de la base de données
public class Device {

    @Id
    private String id;

    @Column(name = "utilisateur_affecte")
    private String utilisateurAffecte;

    @Column(name = "etat")
    private String etat;

    @Column(name = "configuration")
    private String configuration;

    // Accesseurs et modificateurs pour chaque champ
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUtilisateurAffecte() {
        return utilisateurAffecte;
    }

    public void setUtilisateurAffecte(String utilisateurAffecte) {
        this.utilisateurAffecte = utilisateurAffecte;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    // Représentation sous forme de chaîne pour le débogage
    @Override
    public String toString() {
        return String.format("Device{id='%s', utilisateurAffecte='%s', etat='%s', configuration='%s'}", 
                             id, utilisateurAffecte, etat, configuration);
    }
}
