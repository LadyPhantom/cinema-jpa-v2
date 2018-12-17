package fr.maskerad.cinema.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "films")
public class Film {

    private long id;
    private String titre;
    private Double notation;
    private String affiche;
    private String resume;
//    private Personne realisateur;
//    private List<Role> roles=new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 80)
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Basic
    @Column(name = "rating", nullable = true)
    public double getNotation() { return notation; }

    public void setNotation(double notation) { this.notation = notation; }

    @Basic
    @Column(name = "image_path", nullable = true, length = 80)
    public String getAffiche() {
        return affiche;
    }

    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }

    @Basic
    @Column(name = "summary", nullable = true)
    public String getResume() { return affiche; }

    public void setResume(String resume) { this.resume = resume; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Film films = (Film) o;

        if (id != films.id) return false;
        if (titre != null ? !titre.equals(films.titre) : films.titre != null) return false;
        if (resume != null ? !resume.equals(films.resume) : films.resume != null) return false;
        if (affiche != null ? !affiche.equals(films.affiche) : films.affiche != null) return false;
        if (notation != null){
            if (notation != films.notation){
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return "Film{" +
                "titre='" + titre + '\'' +
                ", notation=" + notation +
                ", resume='" + resume + '\'' +
//                ", realisateur=" + realisateur.getNom() +
                '}';
    }
}
