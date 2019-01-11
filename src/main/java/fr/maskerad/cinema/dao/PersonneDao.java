package fr.maskerad.cinema.dao;

import fr.maskerad.cinema.model.Personne;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonneDao extends CrudRepository<Personne, Long> {
//    @Autowired
//    private EntityManager entityManager;
//
//    @Transactional
//    public void save(Personne p){
//        entityManager.merge(p);
//    }
//
//    public List<Personne> getAll(){
//        Query query = entityManager.createQuery("Select p from Personne p");
//        return query.getResultList();
//    }
//
//    public Personne getById(long id){
//        Personne retVal = null;
//        Query query = entityManager.createQuery("select p from Personne p where p.id = :id");
//        query.setParameter("id", id);
//        List<Personne> persons = query.getResultList();
//        if(!persons.isEmpty()){
//            retVal = persons.get(0);
//        }
//        return retVal;
//    }

    public List<Personne> findAllByOrderBySurname();

}
