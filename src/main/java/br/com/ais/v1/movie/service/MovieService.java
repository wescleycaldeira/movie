package br.com.ais.v1.movie.service;

import br.com.ais.base.assertion.AssertionUtil;
import br.com.ais.v1.movie.entity.Movie;
import br.com.ais.v1.movie.entity.MovieDTO;
import org.modelmapper.ModelMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;
import java.util.Objects;
import java.util.UUID;

@Stateless
public class MovieService {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private ModelMapper modelMapper;

    public MovieDTO findById(String id){
        return modelMapper.map(findMovie(UUID.fromString(id)), MovieDTO.class);
    }

    public void save(MovieDTO movieDTO){
        AssertionUtil.isFalse(movieDTO.getId().isEmpty(), "Movie already exists");

        entityManager.persist(modelMapper.map(movieDTO, Movie.class));
    }

    public MovieDTO update(MovieDTO movieDTO){
        AssertionUtil.isTrue(movieDTO.getId().isEmpty(), "Incomplete request param");

        Movie movie = findMovie(UUID.fromString(movieDTO.getId()));

        movie.setName(movieDTO.getName());
        movie.setOverview(movieDTO.getOverview());
        movie.setReleaseDate(movieDTO.getReleaseDate());

        return modelMapper.map(entityManager.merge(movie), MovieDTO.class);
    }

    public void remove(String id){
        Movie movie = findMovie(UUID.fromString(id));

        entityManager.remove(movie);
    }

    private Movie findMovie(UUID uuid){
        Movie movie = entityManager.find(Movie.class, uuid);

        AssertionUtil.isTrue(Objects.isNull(movie), "Movie not found", Response.Status.NOT_FOUND);

        return movie;
    }

}
