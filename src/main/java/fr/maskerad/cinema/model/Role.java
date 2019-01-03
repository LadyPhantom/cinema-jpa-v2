package fr.maskerad.cinema.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="play")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "play_id_seq")
    @NotNull
    private Long id;
    @Basic
    @Column(name = "rank", nullable = false)
    private Integer rank;
    @Basic
    @Column(name = "name", nullable = false, length = 90)
    private String name;
    @ManyToOne
    @JoinColumn(name="person_id")
    private Personne actor;
    @ManyToOne
    @JoinColumn(name="film_id")
    private Film film;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Personne getActor() {
        return actor;
    }

    public void setActor(Personne acteur) {
        this.actor = acteur;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role play = (Role) o;

        if (!id.equals(play.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        return result;
    }
}
