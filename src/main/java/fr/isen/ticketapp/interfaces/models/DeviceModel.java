package fr.isen.ticket.app.interfaces.models;

import fr.isen.ticket.app.interfaces.models.enums.ETAT_DEVICE;

public class DeviceModel {
    private int id;

    private UserModel utilisateur_affecte;

    private String configuration;

    private ETAT_DEVICE etat;

}
