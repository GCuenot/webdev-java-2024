package fr.isen.ticketapp.interfaces;

import fr.isen.ticketapp.interfaces.models.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    // Les méthodes CRUD de base sont incluses : findAll(), persist(), delete(), etc.

    // Méthode personnalisée pour récupérer un utilisateur par son email
    public User findByEmail(String email) {
        return find("email", email).firstResult();  // Recherche un utilisateur par son email
    }

    // Méthode personnalisée pour récupérer un utilisateur par son ID
    public User findById(String id) {
        return find("id", id).firstResult();  // Recherche un utilisateur par son ID
    }
}
