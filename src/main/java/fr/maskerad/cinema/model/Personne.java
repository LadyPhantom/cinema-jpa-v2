package fr.maskerad.cinema.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "persons")
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persons_id_seq")
//    @SequenceGenerator(name = "persons_id_seq")
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "surname", nullable = false, length = 60)
    private String surname;
    @Basic
    @Column(name = "givenname", nullable = true, length = 40)
    private String givenname;
    @Basic
    @Column(name = "birthday", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    @Basic
    @Column(name = "image_path", nullable = true, length = 80)
    private String imagePath;
    @OneToMany(mappedBy = "director")
    private Set<Film> directedFilms;
    @OneToMany(mappedBy = "actor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Role> roles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGivenname() {
        return givenname;
    }

    public void setGivenname(String givenname) {
        this.givenname = givenname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthYear) {
        this.birthday = birthYear;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Set<Film> getDirectedFilms() {
        return directedFilms;
    }

    public void setDirectedFilms(Set<Film> films) {
        this.directedFilms = films;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Personne persons = (Personne) o;

        if (id != persons.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", nom='" + surname + '\'' +
                ", prenom='" + givenname + '\'' +
                ", naissance=" + birthday +
                ", photoPath='" + imagePath + '\'' +
                '}';
    }
}
