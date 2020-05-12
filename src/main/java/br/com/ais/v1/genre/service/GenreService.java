package br.com.ais.v1.genre.service;

import br.com.ais.base.assertion.AssertionUtil;
import br.com.ais.v1.genre.entity.Genre;
import br.com.ais.v1.genre.entity.GenreDTO;
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
public class GenreService {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private ModelMapper modelMapper;

    public GenreDTO findById(String id){
        return modelMapper.map(findGenre(UUID.fromString(id)), GenreDTO.class);
    }

    public void save(GenreDTO genreDTO){
        AssertionUtil.isFalse(genreDTO.getId().isEmpty(), "Genre already exists");

        entityManager.persist(modelMapper.map(genreDTO, Genre.class));
    }

    public GenreDTO update(GenreDTO genreDTO){
        AssertionUtil.isTrue(genreDTO.getId().isEmpty(), "Incomplete request param");

        Genre genre = findGenre(UUID.fromString(genreDTO.getId()));

        genre.setName(genreDTO.getName());
        genre.setDescription(genreDTO.getDescription());

        return modelMapper.map(entityManager.merge(genre), GenreDTO.class);
    }

    public void remove(String id){
        Genre genre = findGenre(UUID.fromString(id));

        entityManager.remove(genre);
    }

    private Genre findGenre(UUID uuid){
        Genre genre = entityManager.find(Genre.class, uuid);

        AssertionUtil.isTrue(Objects.isNull(genre), "Genre not found", Response.Status.NOT_FOUND);

        return genre;
    }

}
