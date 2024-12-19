package fr.isen.ticket.app.interfaces.models;

import java.util.Date;
import fr.isen.ticket.app.interfaces.models.enums.ETAT_TICKET;
import fr.isen.ticket.app.interfaces.models.enums.IMPACT;

public class TicketModel {
    private String titre;

    private String description;

    private IMPACT impact;

    private ETAT_TICKET etat;

    private UtilisateurModel utilisateur_createur;

    private String type_demande;

    private PosteModel poste_informatique;

    private Date date_mise_a_jour;

    private Date date_creation;

    private int id;

}
