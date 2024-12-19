package fr.isen.ticket.app.interfaces.services;

import java.util.List;
import fr.isen.ticket.app.interfaces.models.TicketModel;
import fr.isen.ticket.app.interfaces.models.UtilisateurModel;

public interface UtilisateurService {
    TicketModel creerUtilisateur(final TicketModel ticket);

    UtilisateurModel mettreAJourUtilisateur(final UtilisateurModel ticket);

    void supprimerUtilisateur(final int id);

    UtilisateurModel recupererUtilisateur(final int id);

    List<UtilisateurModel> listerUtilisateur();

}
