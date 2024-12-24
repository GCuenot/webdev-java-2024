package fr.isen.ticketapp;

import fr.isen.ticketapp.interfaces.UserRepository;
import fr.isen.ticketapp.interfaces.models.User;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private final LecteurJSON lecteurJSON;

    // Constructeur de la classe UserResource
    public UserResource() {
        this.lecteurJSON = new LecteurJSON(); // Initialisation de la classe LecteurJSON
    }

    // Méthode utilitaire pour récupérer le tableau des utilisateurs à partir du fichier JSON
    private JSONArray getUserArray() throws IOException {
        String jsonContent = lecteurJSON.lireJSON("user.json"); // Lecture du fichier JSON
        JSONObject jsonObject = new JSONObject(jsonContent);  // Création de l'objet JSON
        return jsonObject.getJSONArray("user");  // Retourne le tableau des utilisateurs
    }

    @Inject
    UserRepository userRepository;  // Injection du UserRepository

    // GET : Récupérer tous les utilisateurs (format JSON)
    @GET
    @Path("/json")
    public Response getAllUsers() {
        try {
            JSONArray userArray = getUserArray();  // Récupère les utilisateurs depuis le fichier JSON
            return Response.ok(userArray.toString()).build();  // Renvoie le tableau en réponse
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur lors de la lecture des utilisateurs.").build();  // Gestion d'erreur
        }
    }

    // GET : Récupérer un utilisateur par son ID
    @GET
    @Path("/json/{id}")
    public Response getUserById(@PathParam("id") String id) {
        try {
            JSONArray userArray = getUserArray();  // Récupère les utilisateurs depuis le fichier JSON
            for (int i = 0; i < userArray.length(); i++) {
                JSONObject user = userArray.getJSONObject(i);
                if (user.getString("id").equals(id)) {
                    return Response.ok(user.toString()).build();  // Renvoie l'utilisateur trouvé
                }
            }
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Utilisateur non trouvé.").build();  // Gestion de l'absence d'utilisateur
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur lors de la lecture des utilisateurs.").build();
        }
    }

    // POST : Créer un nouvel utilisateur
    @POST
    @Path("/json")
    public Response createUser(String input) {
        try {
            JSONObject newUser = new JSONObject(input);  // Parse l'entrée pour créer un nouvel utilisateur
            JSONArray userArray = getUserArray();  // Récupère le tableau des utilisateurs
            userArray.put(newUser);  // Ajoute le nouvel utilisateur au tableau

            // Retourne l'utilisateur créé avec un statut CREATED
            return Response.status(Response.Status.CREATED).entity(newUser.toString()).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur lors de la création de l'utilisateur.").build();
        }
    }

    // PUT : Mettre à jour un utilisateur existant
    @PUT
    @Path("/json/{id}")
    public Response updateUser(@PathParam("id") String id, String input) {
        try {
            JSONObject updatedFields = new JSONObject(input);  // Récupère les champs à mettre à jour
            JSONArray userArray = getUserArray();  // Récupère le tableau des utilisateurs

            for (int i = 0; i < userArray.length(); i++) {
                JSONObject user = userArray.getJSONObject(i);
                if (user.getString("id").equals(id)) {
                    // Met à jour les champs de l'utilisateur
                    for (String key : updatedFields.keySet()) {
                        user.put(key, updatedFields.get(key));
                    }
                    return Response.ok("Utilisateur mis à jour.").build();
                }
            }
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Utilisateur non trouvé.").build();  // Gestion de l'absence d'utilisateur
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur lors de la mise à jour de l'utilisateur.").build();
        }
    }

    // DELETE : Supprimer un utilisateur
    @DELETE
    @Path("/json/{id}")
    public Response deleteUser(@PathParam("id") String id) {
        try {
            JSONArray userArray = getUserArray();  // Récupère le tableau des utilisateurs

            for (int i = 0; i < userArray.length(); i++) {
                JSONObject user = userArray.getJSONObject(i);
                if (user.getString("id").equals(id)) {
                    userArray.remove(i);  // Supprime l'utilisateur
                    return Response.noContent().build();  // Retourne un code 204 No Content
                }
            }
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Utilisateur non trouvé.").build();  // Gestion de l'absence d'utilisateur
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur lors de la suppression de l'utilisateur.").build();
        }
    }

    // GET : Récupérer tous les utilisateurs depuis la base de données
    @GET
    @Path("/bdd")
    public List<User> getAllUsersBDD() {
        return userRepository.listAll();  // Renvoie tous les utilisateurs depuis la base de données
    }

    // GET : Récupérer un utilisateur par son ID depuis la base de données
    @GET
    @Path("/bdd/{id}")
    public User getUserByIdBDD(@PathParam("id") String id) {
        User user = userRepository.find("id", id).firstResult();  // Recherche l'utilisateur dans la BDD
        if (user == null) {
            throw new NotFoundException("Utilisateur non trouvé.");  // Lancer une exception si non trouvé
        }
        return user;
    }
}
