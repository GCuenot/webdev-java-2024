package fr.isen.ticket.app.interfaces.services;

import java.util.List;
import fr.isen.ticket.app.interfaces.models.TicketModel;

public interface TicketService {
    TicketModel creerTicket(final TicketModel ticket);

    TicketModel mettreAJourTicket(final TicketModel ticket);

    void supprimerTicket(final int id);

    TicketModel recupererTicket(final int id);

    List<TicketModel> listerTicket();

}
