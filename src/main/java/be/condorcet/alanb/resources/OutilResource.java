package be.condorcet.alanb.resources;

import be.condorcet.alanb.entities.Outil;
import be.condorcet.alanb.services.OutilService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/tools")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OutilResource {

    @Inject
    private OutilService outilService;

    @GET
    public List<Outil> getAll() {
        return outilService.getAllOutils();
    }

    // GET /api/tools/category/Jardinage
    @GET
    @Path("/category/{cat}")
    public List<Outil> getByCategory(@PathParam("cat") String cat) {
        return outilService.getOutilsByCategorie(cat);
    }

    // POST /api/tools/user/{userId}
    // On doit préciser l'ID du propriétaire dans l'URL pour lier l'outil
    @POST
    @Path("/user/{userId}")
    public Response createOutilForUser(Outil outil, @PathParam("userId") Long userId) {
        try {
            Outil newTool = outilService.addOutilToUtilisateur(outil, userId);
            return Response.status(Response.Status.CREATED).entity(newTool).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}