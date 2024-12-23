package org.acme;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private final LecteurJSON lecteurJSON;

    public UserResource() {
        this.lecteurJSON = new LecteurJSON(); // Initialisation de la classe LecteurJSON
    }

    private JSONArray getUserArray() throws IOException {
        String jsonContent = lecteurJSON.lireJSON("user.json");
        JSONObject jsonObject = new JSONObject(jsonContent);
        return jsonObject.getJSONArray("user");
    }

    @GET
    public Response getAllUsers() {
        try {
            JSONArray userArray = getUserArray();
            return Response.ok(userArray.toString()).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la lecture des user.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") String id) {
        try {
            JSONArray userArray = getUserArray();
            for (int i = 0; i < userArray.length(); i++) {
                JSONObject user = userArray.getJSONObject(i);
                if (user.getString("id").equals(id)) {
                    return Response.ok(user.toString()).build();
                }
            }
            return Response.status(Response.Status.NOT_FOUND).entity("User non trouvé.").build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la lecture des user.").build();
        }
    }

    @POST
    public Response createUser(String input) {
        try {
            JSONObject newUser = new JSONObject(input);
            JSONArray userArray = getUserArray();
            userArray.put(newUser);

            return Response.status(Response.Status.CREATED).entity(newUser.toString()).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la création du user.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") String id, String input) {
        try {
            JSONObject updatedFields = new JSONObject(input);
            JSONArray userArray = getUserArray();

            for (int i = 0; i < userArray.length(); i++) {
                JSONObject user = userArray.getJSONObject(i);
                if (user.getString("id").equals(id)) {
                    for (String key : updatedFields.keySet()) {
                        user.put(key, updatedFields.get(key));
                    }
                    return Response.ok("User mis à jour.").build();
                }
            }
            return Response.status(Response.Status.NOT_FOUND).entity("User non trouvé.").build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la mise à jour du user.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") String id) {
        try {
            JSONArray userArray = getUserArray();

            for (int i = 0; i < userArray.length(); i++) {
                JSONObject user = userArray.getJSONObject(i);
                if (user.getString("id").equals(id)) {
                    userArray.remove(i);
                    return Response.noContent().build();
                }
            }
            return Response.status(Response.Status.NOT_FOUND).entity("User non trouvé.").build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la suppression du user.").build();
        }
    }
}
