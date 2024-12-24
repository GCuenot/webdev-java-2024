package fr.isen.ticketapp.interfaces;

import fr.isen.ticketapp.interfaces.models.Device;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeviceRepository implements PanacheRepository<Device> {

    // Méthode pour insérer un device dans la base de données
    public Device createDevice(Device device) {
        persist(device);  // Persiste le device dans la BDD
        return device;  // Retourne le device après l'insertion
    }
}
