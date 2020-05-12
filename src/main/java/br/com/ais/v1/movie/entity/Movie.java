package br.com.ais.v1.movie.entity;

import br.com.ais.base.entity.AuditEntity;
import br.com.ais.base.entity.AuditListener;
import br.com.ais.base.entity.Auditable;
import br.com.ais.base.entity.BaseEntity;
import br.com.ais.v1.genre.entity.Genre;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Movie")
@Table(name = "movie")
@EntityListeners(AuditListener.class)
public class Movie extends BaseEntity implements Auditable {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "overview", nullable = false)
    private String overview;

    @Column(name = "releaseDate", nullable = false)
    private LocalDate releaseDate;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "movieGender",
            joinColumns = @JoinColumn(name = "movie_uuid"),
            inverseJoinColumns = @JoinColumn(name = "genre_uuid")
    )
    private Set<Genre> genres = new HashSet<>();

    @Embedded
    private AuditEntity auditEntity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public AuditEntity getAuditEntity() {
        return auditEntity;
    }

    @Override
    public void setAuditEntity(AuditEntity auditEntity) {
        this.auditEntity = auditEntity;
    }
}
