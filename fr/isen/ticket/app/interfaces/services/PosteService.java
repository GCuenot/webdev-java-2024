package fr.isen.ticket.app.interfaces.services;

import java.util.List;
import fr.isen.ticket.app.interfaces.models.PosteModel;

public interface PosteService {
    PosteModel creerPoste(final PosteModel ticket);

    PosteModel mettreAJourPoste(final PosteModel ticket);

    void supprimerPoste(final int id);

    PosteModel recupererPoste(final int id);

    List<PosteModel> listerPoste();

}
