package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

@Path("/ticket")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TicketResource {

        private final LecteurJSON lecteurJSON;

        public TicketResource() {
            this.lecteurJSON = new LecteurJSON(); // Initialisation de la classe LecteurJSON
        }

    // Méthode pour obtenir le tableau de tickets depuis le JSON
    private JSONArray getTicketsArray() throws IOException {
        String jsonContent = lecteurJSON.lireJSON("ticket.json"); // Appel à la méthode lireJSON
        JSONObject jsonObject = new JSONObject(jsonContent);
        return jsonObject.getJSONArray("ticket");
    }

    // GET : Récupérer tous les tickets
    @GET
    public Response getAllTickets() {
        try {
            JSONArray ticketsArray = getTicketsArray();
            return Response.ok(ticketsArray.toString()).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur lors de la lecture des tickets.").build();
        }
    }

    // GET : Récupérer un ticket par ID
    @GET
    @Path("/{id}")
    public Response getTicketById(@PathParam("id") String id) {
        try {
            JSONArray ticketsArray = getTicketsArray();
            for (int i = 0; i < ticketsArray.length(); i++) {
                JSONObject ticket = ticketsArray.getJSONObject(i);
                if (ticket.getString("id").equals(id)) {
                    return Response.ok(ticket.toString()).build();
                }
            }
            return Response.status(Response.Status.NOT_FOUND).entity("Ticket non trouvé.").build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur lors de la lecture des tickets.").build();
        }
    }

    // POST : Créer un nouveau ticket
    @POST
    public Response createTicket(String input) {
        try {
            JSONObject newTicket = new JSONObject(input);
            JSONArray ticketsArray = getTicketsArray();
            ticketsArray.put(newTicket);

            // Retourne le ticket créé
            return Response.status(Response.Status.CREATED).entity(newTicket.toString()).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur lors de la création du ticket.").build();
        }
    }

    // PUT : Mettre à jour un ticket
    @PUT
    @Path("/{id}")
    public Response updateTicket(@PathParam("id") String id, String input) {
        try {
            JSONObject updatedFields = new JSONObject(input);
            JSONArray ticketsArray = getTicketsArray();

            for (int i = 0; i < ticketsArray.length(); i++) {
                JSONObject ticket = ticketsArray.getJSONObject(i);
                if (ticket.getString("id").equals(id)) {
                    for (String key : updatedFields.keySet()) {
                        ticket.put(key, updatedFields.get(key));
                    }
                    return Response.ok("Ticket mis à jour.").build();
                }
            }
            return Response.status(Response.Status.NOT_FOUND).entity("Ticket non trouvé.").build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur lors de la mise à jour du ticket.").build();
        }
    }

    // DELETE : Supprimer un ticket
    @DELETE
    @Path("/{id}")
    public Response deleteTicket(@PathParam("id") String id) {
        try {
            JSONArray ticketsArray = getTicketsArray();

            for (int i = 0; i < ticketsArray.length(); i++) {
                JSONObject ticket = ticketsArray.getJSONObject(i);
                if (ticket.getString("id").equals(id)) {
                    ticketsArray.remove(i);
                    return Response.noContent().build();
                }
            }
            return Response.status(Response.Status.NOT_FOUND).entity("Ticket non trouvé.").build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur lors de la suppression du ticket.").build();
        }

    }
}
