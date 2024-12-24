package fr.isen.ticket.app.interfaces.services;

import java.util.List;
import fr.isen.ticket.app.interfaces.models.TicketModel; // Assurez-vous d'utiliser le bon modèle

public interface TicketService {
    
    // Crée un nouveau ticket et renvoie l'objet créé
    TicketModel enregistrerTicket(final TicketModel ticket);

    // Met à jour un ticket existant et renvoie l'objet mis à jour
    TicketModel modifierTicket(final TicketModel ticket);

    // Supprime un ticket en fonction de son identifiant
    void annulerTicket(final int idTicket);

    // Récupère un ticket à partir de son identifiant
    TicketModel recupererTicketParId(final int idTicket);

    // Récupère tous les tickets existants
    List<TicketModel> recupererTousLesTickets();
}
