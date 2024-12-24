package fr.isen.ticket.app.interfaces.models;

import java.util.Date;
import fr.isen.ticket.app.interfaces.models.enums.ETAT_TICKET;
import fr.isen.ticket.app.interfaces.models.enums.IMPACT;

public class TicketModel {
    private String titre;

    private String description;

    private IMPACT impact;

    private ETAT_TICKET etat;

    private UserModel utilisateur_createur;

    private String type_demande;

    private DeviceModel poste_informatique;

    private Date date_mise_a_jour;

    private Date date_creation;

    private int id;

}
