package br.com.ais.v1.movie.resources;

import br.com.ais.v1.genre.entity.GenreDTO;
import br.com.ais.v1.movie.entity.MovieDTO;
import br.com.ais.v1.movie.service.MovieService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/v1/movie")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MovieResource {

    @Inject
    private MovieService movieService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") String id){
        MovieDTO movieDTO = movieService.findById(id);

        return Response.ok(movieDTO).build();
    }

    @POST
    public Response save(@Valid MovieDTO movieDTO){
        movieService.save(movieDTO);

        return Response.ok().build();
    }

    @PUT
    public Response update(@Valid MovieDTO movieDTO){
        movieService.update(movieDTO);

        return Response.ok(movieDTO).build();
    }

    @DELETE
    @Path("/{id}")
    public Response remove(@PathParam("id") String id){
        movieService.remove(id);

        return Response.ok().build();
    }
}
