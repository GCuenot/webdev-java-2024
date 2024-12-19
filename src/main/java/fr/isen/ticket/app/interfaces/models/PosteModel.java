package fr.isen.ticket.app.interfaces.models;

import fr.isen.ticket.app.interfaces.models.enums.ETAT_POSTE;

public class PosteModel {
    private int id;

    private UtilisateurModel utilisateur_affecte;

    private String configuration;

    private ETAT_POSTE etat;

}
