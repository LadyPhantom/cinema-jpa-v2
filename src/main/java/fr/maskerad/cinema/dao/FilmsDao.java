package fr.maskerad.cinema.dao;

import java.util.List;
import fr.maskerad.cinema.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public interface FilmsDao extends CrudRepository<Film, Long> {
//
//    @Autowired
//    private EntityManager entityManager;
//
//    @Transactional
//    public void save(Film f){
//        entityManager.merge(f);
//    }
//
//    public List<Film> getAll(){
//        Query query = entityManager.createQuery("Select f from Film f");
//        return query.getResultList();
//    }
//
//    public Film getById(long id){
//        Film retVal = null;
//        Query query = entityManager.createQuery("select f from Film f where f.id = :id");
//        query.setParameter("id", id);
//        List<Film> films = query.getResultList();
//        if(!films.isEmpty()){
//            retVal = films.get(0);
//        }
//        return retVal;
//    }
//    @Query(value = "", nativeQuery = true)
//    void save();

    public List<Film> findAllByOrderByTitle();

}
