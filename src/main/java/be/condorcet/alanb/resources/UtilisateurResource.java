package be.condorcet.alanb.resources;

import be.condorcet.alanb.entities.Utilisateur;
import be.condorcet.alanb.services.UtilisateurService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/users") // URL : http://localhost:8080/AlanB_Tools/api/users
@Produces(MediaType.APPLICATION_JSON) // On renvoie du JSON
@Consumes(MediaType.APPLICATION_JSON) // On accepte du JSON
public class UtilisateurResource {

    @Inject
    private UtilisateurService utilisateurService;

    // GET /api/users -> Lister tout le monde
    @GET
    public List<Utilisateur> getAll() {
        return utilisateurService.getAll();
    }

    // GET /api/users/{id} -> Un seul user
    @GET
    @Path("/{id}")
    public Response getOne(@PathParam("id") Long id) {
        Utilisateur u = utilisateurService.getById(id);
        if (u == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Utilisateur introuvable").build();
        }
        return Response.ok(u).build();
    }

    // POST /api/users -> Créer un user
    @POST
    public Response create(Utilisateur u) {
        try {
            // On appelle le service qui contient les règles métier (Validation email, etc.)
            Utilisateur createdUser = utilisateurService.createUtilisateur(u);
            return Response.status(Response.Status.CREATED).entity(createdUser).build();
        } catch (Exception e) {
            // Si le service lance une erreur (ex: "Email invalide"), on renvoie 400 Bad Request
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // DELETE /api/users/{id}
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        utilisateurService.delete(id);
        return Response.noContent().build(); // 204 No Content
    }
}