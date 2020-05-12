package br.com.ais.v1.genre.resources;

import br.com.ais.v1.genre.entity.GenreDTO;
import br.com.ais.v1.genre.service.GenreService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/v1/gender")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GenreResource {

    @Inject
    private GenreService genreService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") String id){
        GenreDTO genreDTO = genreService.findById(id);

        return Response.ok(genreDTO).build();
    }

    @POST
    public Response save(GenreDTO genreDTO){
        genreService.save(genreDTO);

        return Response.ok().build();
    }

    @PUT
    public Response update(GenreDTO genreDTO){
        GenreDTO update = genreService.update(genreDTO);

        return Response.ok(update).build();
    }

    @DELETE
    @Path("{/id}")
    public Response remove(@PathParam("id") String id){
        genreService.remove(id);

        return Response.ok().build();
    }
}
