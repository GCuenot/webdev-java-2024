package org.acme;

import java.io.InputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class LecteurJSON {

    public String lireJSON(String json) throws IOException {
        // Chargement du fichier JSON depuis les ressources
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(json);

        if (inputStream == null) {
            throw new IOException("Le fichier JSON n'a pas pu être trouvé.");
        }

        // Utilisation d'un Scanner pour lire l'InputStream et convertir en String
        try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
            return scanner.useDelimiter("\\A").next(); // \\A permet de lire tout le contenu
        }
    }
}
