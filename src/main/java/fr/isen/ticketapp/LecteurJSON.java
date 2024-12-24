package fr.isen.ticketapp;

import java.io.InputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class LecteurJSON {

    // Méthode pour lire le contenu d'un fichier JSON à partir des ressources
    public String lireJSON(String json) throws IOException {
        // Chargement du fichier JSON depuis les ressources
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(json);

        // Vérifie si le fichier JSON existe
        if (inputStream == null) {
            throw new IOException("Le fichier JSON n'a pas pu être trouvé."); // Gestion d'erreur si le fichier est introuvable
        }

        // Utilisation d'un Scanner pour lire l'InputStream et convertir le contenu en String
        try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
            return scanner.useDelimiter("\\A").next(); // \\A permet de lire tout le contenu du fichier
        }
    }
}
