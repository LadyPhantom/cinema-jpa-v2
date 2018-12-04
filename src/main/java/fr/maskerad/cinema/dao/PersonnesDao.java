package fr.maskerad.cinema.dao;

import fr.maskerad.cinema.model.Film;
import fr.maskerad.cinema.model.Personne;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fred on 03/02/2016.
 */

@Component
public class PersonnesDao {
    List<Personne> personnes = new ArrayList<>();

    public PersonnesDao(){

        personnes.add(new Personne("Noiret", "Phillipe", 1930, "p0001.jpg"));
        personnes.add(new Personne("Galabru", "Michel", 1922, "p0002.jpg"));
        personnes.add(new Personne("Huppert", "Isabelle", 1953, "p0003.jpg"));
        personnes.add(new Personne("Hamill", "Mark", 1951, "p0004.jpg"));
        personnes.add(new Personne("Ford", "Harrison", 1942, "p0005.jpg"));
        personnes.add(new Personne("Fisher", "Carrie", 1956, "p0006.jpg"));
        personnes.add(new Personne("Gere", "Richard", 1949, "p0007.jpg"));
        personnes.add(new Personne("Roberts", "Julia", 1967, "p0008.jpg"));
        personnes.add(new Personne("Bellamy", "Ralph", 1904, "p0009.jpg"));
        personnes.add(new Personne("Hopkins", "Anthony", 1937, "p0010.jpg"));
        personnes.add(new Personne("Pitt", "William Bradley", 1963, "p0011.jpg"));
        personnes.add(new Personne("Quinn", "Aidan", 1959, "p0012.jpg"));
        personnes.add(new Personne("Thomas", "Henry", 1971, "p0013.jpg"));
        personnes.add(new Personne("Barrymore", "Drew Bythe", 1975, "p0014.jpg"));
        personnes.add(new Personne("Wallace Stone", "Dee", 1948, "p0015.jpg"));
        personnes.add(new Personne("Heston", "Charlton", 1923, "p0016.jpg"));
        personnes.add(new Personne("Boyd", "Stephen", 1928, "p0017.jpg"));
        personnes.add(new Personne("Hawkins", "Jack", 1910, "p0018.jpg"));

    }

    public List<Personne> personnes(){
        return personnes;
    }

//    public Film getById(int id){
//        Film film = null;
//        for (Film f: lesFilms
//        ) {
//            if(f.getId() == id){
//                film = f;
//            }
//        }
//        return film;
//    }

    public Personne getByFirstName(String fName){
        Personne personne = null;

        for (Personne p : personnes){
            if ( p.getPrenom().equals(fName) ){
                personne = p;
            }
        }
        return personne;
    }

    public Personne getByLastName(String lName){
        Personne personne = null;

        for (Personne p : personnes){
            if ( p.getNom().equals(lName) ){
                personne = p;
            }
        }
        return personne;
    }

}
