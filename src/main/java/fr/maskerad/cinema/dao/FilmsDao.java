package fr.maskerad.cinema.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import fr.maskerad.cinema.model.Film;
import fr.maskerad.cinema.model.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Created by fred on 03/02/2016.
 */

@Component
public class FilmsDao {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void save(Film f){
        entityManager.merge(f);
    }

    public List<Film> getAll(){
        Query query = entityManager.createQuery("Select f from Film f");
        return query.getResultList();
    }

    public Film getById(long id){
        Film retVal = null;
        Query query = entityManager.createQuery("select f from Film f where f.id = :id");
        query.setParameter("id", id);
        List<Film> films = query.getResultList();
        if(!films.isEmpty()){
            retVal = films.get(0);
        }
        return retVal;
    }

}
