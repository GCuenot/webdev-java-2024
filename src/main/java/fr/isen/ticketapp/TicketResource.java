package fr.isen.ticketapp;

import fr.isen.ticketapp.interfaces.TicketRepository;
import fr.isen.ticketapp.interfaces.models.Ticket;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

@Path("/ticket")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TicketResource {

    private final LecteurJSON lecteurJSON;

    @Inject
    TicketRepository ticketRepository;

    public TicketResource() {
        this.lecteurJSON = new LecteurJSON();
    }

    // Méthode utilitaire pour lire le fichier JSON
    private JSONArray getTicketArray() throws IOException {
        String jsonContent = lecteurJSON.lireJSON("ticket.json");
        return new JSONObject(jsonContent).getJSONArray("ticket");
    }

    // Récupérer tous les tickets du fichier JSON
    @GET
    @Path("/json")
    public Response getAllTicket() {
        try {
            JSONArray ticketArray = getTicketArray();
            return Response.ok(ticketArray.toString()).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur de lecture des tickets.").build();
        }
    }

    // Récupérer un ticket par son ID depuis le fichier JSON
    @GET
    @Path("/json/{id}")
    public Response getTicketById(@PathParam("id") String id) {
        try {
            JSONArray ticketArray = getTicketArray();
            for (int i = 0; i < ticketArray.length(); i++) {
                JSONObject ticket = ticketArray.getJSONObject(i);
                if (ticket.getString("id").equals(id)) {
                    return Response.ok(ticket.toString()).build();
                }
            }
            return Response.status(Response.Status.NOT_FOUND).entity("Ticket introuvable.").build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur de lecture des tickets.").build();
        }
    }

    // Ajouter un nouveau ticket au fichier JSON
    @POST
    @Path("/json")
    public Response createTicket(String input) {
        try {
            JSONObject newTicket = new JSONObject(input);
            JSONArray ticketArray = getTicketArray();
            ticketArray.put(newTicket);
            return Response.status(Response.Status.CREATED).entity(newTicket.toString()).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur de création du ticket.").build();
        }
    }

    // Mettre à jour un ticket dans le fichier JSON
    @PUT
    @Path("/json/{id}")
    public Response updateTicket(@PathParam("id") String id, String input) {
        try {
            JSONObject updatedFields = new JSONObject(input);
            JSONArray ticketArray = getTicketArray();
            for (int i = 0; i < ticketArray.length(); i++) {
                JSONObject ticket = ticketArray.getJSONObject(i);
                if (ticket.getString("id").equals(id)) {
                    updatedFields.keySet().forEach(key -> ticket.put(key, updatedFields.get(key)));
                    return Response.ok("Ticket mis à jour.").build();
                }
            }
            return Response.status(Response.Status.NOT_FOUND).entity("Ticket introuvable.").build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur de mise à jour du ticket.").build();
        }
    }

    // Supprimer un ticket du fichier JSON
    @DELETE
    @Path("/json/{id}")
    public Response deleteTicket(@PathParam("id") String id) {
        try {
            JSONArray ticketArray = getTicketArray();
            for (int i = 0; i < ticketArray.length(); i++) {
                JSONObject ticket = ticketArray.getJSONObject(i);
                if (ticket.getString("id").equals(id)) {
                    ticketArray.remove(i);
                    return Response.noContent().build();
                }
            }
            return Response.status(Response.Status.NOT_FOUND).entity("Ticket introuvable.").build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur de suppression du ticket.").build();
        }
    }

    // SECTION BDD : Gestion des tickets en base de données

    @GET
    @Path("/bdd")
    public List<Ticket> getAllTicketBDD() {
        return ticketRepository.listAll(); 
    }

    @GET
    @Path("/bdd/{id}")
    public Ticket getTicketByIdBDD(@PathParam("id") String id) {
        Ticket ticket = ticketRepository.find("id", id).firstResult();
        if (ticket == null) {
            throw new NotFoundException("Ticket introuvable.");
        }
        return ticket;
    }

    @POST
    @Path("/bdd")
    public Response createTicketBDD(Ticket ticket) {
        ticketRepository.persist(ticket);
        return Response.status(Response.Status.CREATED).entity(ticket).build();
    }

    @PUT
    @Path("/bdd/{id}")
    public Response updateTicketBDD(@PathParam("id") String id, Ticket updatedTicket) {
        Ticket ticket = ticketRepository.find("id", id).firstResult();
        if (ticket == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Ticket introuvable.").build();
        }
        ticket.setTitle(updatedTicket.getTitle());
        ticket.setDescription(updatedTicket.getDescription());
        ticketRepository.persist(ticket);
        return Response.ok("Ticket mis à jour.").build();
    }

    @DELETE
    @Path("/bdd/{id}")
    public Response deleteTicketBDD(@PathParam("id") String id) {
        Ticket ticket = ticketRepository.find("id", id).firstResult();
        if (ticket == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Ticket introuvable.").build();
        }
        ticketRepository.delete(ticket);
        return Response.noContent().build();
    }
}
