package fr.maskerad.cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "genres")
public class Genre {
    /**
     * L'identifiant unique dans la base
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Le nom du genre
     */
    @Basic
    @Unique
    @Size(min = 3, max = 30, message = "La longueur du genre doit être comprise entre 3 et 30.")
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    /**
     * L'ensemble des films associés au genre
     */
    @ManyToMany(mappedBy = "genres")
    @JsonIgnore
    private Set<Film> films;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genre genre = (Genre) o;

        if (id != genre.id) return false;
        if (name != null ? !name.equals(genre.name) : genre.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
