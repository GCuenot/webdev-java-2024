package fr.isen.ticket.app.interfaces.services;

import java.util.List;
import fr.isen.ticket.app.interfaces.models.UserModel;

public interface UserService {

    // Crée un nouvel utilisateur et renvoie l'objet créé
    UserModel ajouterUtilisateur(final UserModel utilisateur);

    // Met à jour un utilisateur existant et renvoie l'objet mis à jour
    UserModel modifierUtilisateur(final UserModel utilisateur);

    // Supprime un utilisateur en fonction de son identifiant
    void retirerUtilisateur(final int idUtilisateur);

    // Récupère un utilisateur par son identifiant
    UserModel recupererUtilisateurParId(final int idUtilisateur);

    // Récupère tous les utilisateurs existants
    List<UserModel> recupererTousLesUtilisateurs();
}
