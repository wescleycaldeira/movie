package br.com.ais.v1.genre.entity;

import br.com.ais.base.entity.AuditEntity;
import br.com.ais.base.entity.AuditListener;
import br.com.ais.base.entity.Auditable;
import br.com.ais.base.entity.BaseEntity;
import br.com.ais.v1.movie.entity.Movie;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Gender")
@Table(name = "gender")
@EntityListeners(AuditListener.class)
public class Genre extends BaseEntity implements Auditable {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "genres")
    private Set<Movie> movies = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Embedded
    private AuditEntity auditEntity;

    @Override
    public AuditEntity getAuditEntity() {
        return auditEntity;
    }

    @Override
    public void setAuditEntity(AuditEntity auditEntity) {
        this.auditEntity = auditEntity;
    }
}
