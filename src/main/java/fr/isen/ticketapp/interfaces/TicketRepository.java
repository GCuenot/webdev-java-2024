package fr.isen.ticketapp.interfaces;

import fr.isen.ticketapp.interfaces.models.Ticket;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TicketRepository implements PanacheRepository<Ticket> {
    // Les méthodes CRUD de base sont incluses : findAll(), persist(), delete(), etc.
    // Ce repository peut être étendu pour ajouter des méthodes personnalisées si nécessaire
}
