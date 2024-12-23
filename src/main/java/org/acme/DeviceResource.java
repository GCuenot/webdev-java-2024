package org.acme;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

@Path("/device")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeviceResource {

    private final LecteurJSON lecteurJSON;

    public DeviceResource() {
        this.lecteurJSON = new LecteurJSON();
    }

    private JSONArray getDeviceArray() throws IOException {
        String jsonContent = lecteurJSON.lireJSON("device.json");
        JSONObject jsonObject = new JSONObject(jsonContent);
        return jsonObject.getJSONArray("device");
    }

    @GET
    public Response getAllDevices() {
        try {
            JSONArray deviceArray = getDeviceArray();
            return Response.ok(deviceArray.toString()).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la lecture des device.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getDeviceById(@PathParam("id") String id) {
        try {
            JSONArray deviceArray = getDeviceArray();
            for (int i = 0; i < deviceArray.length(); i++) {
                JSONObject device = deviceArray.getJSONObject(i);
                if (device.getString("id").equals(id)) {
                    return Response.ok(device.toString()).build();
                }
            }
            return Response.status(Response.Status.NOT_FOUND).entity("Device non trouvé.").build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la lecture des device.").build();
        }
    }

    @POST
    public Response createDevice(String input) {
        try {
            JSONObject newDevice = new JSONObject(input);
            JSONArray deviceArray = getDeviceArray();
            deviceArray.put(newDevice);

            return Response.status(Response.Status.CREATED).entity(newDevice.toString()).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la création du device.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateDevice(@PathParam("id") String id, String input) {
        try {
            JSONObject updatedFields = new JSONObject(input);
            JSONArray deviceArray = getDeviceArray();

            for (int i = 0; i < deviceArray.length(); i++) {
                JSONObject device = deviceArray.getJSONObject(i);
                if (device.getString("id").equals(id)) {
                    for (String key : updatedFields.keySet()) {
                        device.put(key, updatedFields.get(key));
                    }
                    return Response.ok("Device mis à jour.").build();
                }
            }
            return Response.status(Response.Status.NOT_FOUND).entity("Device non trouvé.").build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la mise à jour du device.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDevice(@PathParam("id") String id) {
        try {
            JSONArray deviceArray = getDeviceArray();

            for (int i = 0; i < deviceArray.length(); i++) {
                JSONObject device = deviceArray.getJSONObject(i);
                if (device.getString("id").equals(id)) {
                    deviceArray.remove(i);
                    return Response.noContent().build();
                }
            }
            return Response.status(Response.Status.NOT_FOUND).entity("Device non trouvé.").build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la suppression du device.").build();
        }
    }
}
