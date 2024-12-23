package org.acme;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.net.URL;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ds {

    public <T> List<T> getDeviceFromJsonFile(Class<T> cls, String filepath) {
        List<T> elements = new ArrayList<>();

        try {
            // Initialisation de l'ObjectMapper
            ObjectMapper mapper = new ObjectMapper();

            // Chargement du fichier JSON
            URL res = this.getClass().getClassLoader().getResource(filepath);
            if (res == null) {
                throw new IllegalArgumentException("Fichier non trouvé : " + filepath);
            }
            File file = new File(res.getFile());

            // Création d'un tableau générique
            T[] array = (T[]) Array.newInstance(cls, 0);

            // Lecture et conversion du fichier JSON en liste
            array = mapper.readValue(file, (Class<T[]>) array.getClass());
            elements = Arrays.asList(array);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return elements;
    }
}
