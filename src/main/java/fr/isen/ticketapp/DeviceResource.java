package fr.isen.ticketapp;

import fr.isen.ticketapp.interfaces.DeviceRepository;
import fr.isen.ticketapp.interfaces.models.Device; // Importation de la classe Device
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

@Path("/device")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeviceResource {

    @Inject
    DeviceRepository deviceRepository;

    private final LecteurJSON jsonReader;

    public DeviceResource() {
        this.jsonReader = new LecteurJSON(); // Instantiation du lecteur JSON
    }

    // Méthode privée pour obtenir la liste des appareils depuis un fichier JSON
    private JSONArray loadDeviceData() throws IOException {
        String jsonContent = jsonReader.lireJSON("device.json");
        JSONObject jsonObject = new JSONObject(jsonContent);
        return jsonObject.getJSONArray("device");
    }

    // GET : Retourner tous les appareils depuis le fichier JSON
    @GET
    public Response fetchAllDevices() {
        try {
            JSONArray devices = loadDeviceData();
            return Response.ok(devices.toString()).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Problème de lecture des appareils.").build();
        }
    }

    // GET : Retourner un appareil spécifique en fonction de son ID
    @GET
    @Path("/{id}")
    public Response fetchDeviceById(@PathParam("id") String id) {
        try {
            JSONArray devices = loadDeviceData();
            for (int i = 0; i < devices.length(); i++) {
                JSONObject device = devices.getJSONObject(i);
                if (device.getString("id").equals(id)) {
                    return Response.ok(device.toString()).build();
                }
            }
            return Response.status(Response.Status.NOT_FOUND).entity("Appareil non trouvé.").build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur de lecture des appareils.").build();
        }
    }

    // POST : Créer un nouvel appareil
    @POST
    public Response addDevice(String input) {
        try {
            JSONObject newDevice = new JSONObject(input);
            JSONArray devices = loadDeviceData();
            devices.put(newDevice);
            return Response.status(Response.Status.CREATED).entity(newDevice.toString()).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Problème lors de la création de l'appareil.").build();
        }
    }

    // PUT : Mettre à jour un appareil existant
    @PUT
    @Path("/{id}")
    public Response modifyDevice(@PathParam("id") String id, String input) {
        try {
            JSONObject updatedData = new JSONObject(input);
            JSONArray devices = loadDeviceData();

            for (int i = 0; i < devices.length(); i++) {
                JSONObject device = devices.getJSONObject(i);
                if (device.getString("id").equals(id)) {
                    for (String key : updatedData.keySet()) {
                        device.put(key, updatedData.get(key)); // Mise à jour des champs
                    }
                    return Response.ok("Appareil mis à jour.").build();
                }
            }
            return Response.status(Response.Status.NOT_FOUND).entity("Appareil non trouvé.").build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Problème lors de la mise à jour de l'appareil.").build();
        }
    }

    // DELETE : Supprimer un appareil via son ID
    @DELETE
    @Path("/{id}")
    public Response removeDevice(@PathParam("id") String id) {
        try {
            JSONArray devices = loadDeviceData();

            for (int i = 0; i < devices.length(); i++) {
                JSONObject device = devices.getJSONObject(i);
                if (device.getString("id").equals(id)) {
                    devices.remove(i); // Suppression de l'appareil
                    return Response.noContent().build();
                }
            }
            return Response.status(Response.Status.NOT_FOUND).entity("Appareil non trouvé.").build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la suppression de l'appareil.").build();
        }
    }

    // GET : Retourner tous les appareils depuis la base de données
    @GET
    @Path("/bdd")
    public List<Device> fetchDevicesFromDB() {
        return deviceRepository.listAll(); // Liste tous les appareils dans la BDD
    }

    // GET : Retourner un appareil par ID depuis la base de données
    @GET
    @Path("/bdd/{id}")
    public Device fetchDeviceByIdFromDB(@PathParam("id") String id) {
        Device device = deviceRepository.find("id", id).firstResult();
        if (device == null) {
            throw new NotFoundException("Appareil non trouvé.");
        }
        return device;
    }
}
