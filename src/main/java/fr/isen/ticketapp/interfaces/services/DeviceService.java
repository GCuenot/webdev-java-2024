package fr.isen.ticket.app.interfaces.services;

import java.util.List;
import fr.isen.ticket.app.interfaces.models.DeviceModel; // Assurez-vous que le modèle est correct

public interface DeviceService {
    
    // Crée un nouvel appareil et renvoie le modèle créé
    DeviceModel ajouterDevice(final DeviceModel appareil);

    // Met à jour les informations d'un appareil et renvoie l'objet mis à jour
    DeviceModel mettreAJourDevice(final DeviceModel appareil);

    // Supprime un appareil en fonction de son identifiant
    void retirerDevice(final int idAppareil);

    // Récupère un appareil selon son identifiant
    DeviceModel recupererDeviceParId(final int idAppareil);

    // Liste tous les appareils
    List<DeviceModel> recupererTousLesDevices();
}
