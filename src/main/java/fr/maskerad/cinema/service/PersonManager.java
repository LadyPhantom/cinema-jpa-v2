package fr.maskerad.cinema.service;

import fr.maskerad.cinema.dao.PersonneDao;
import fr.maskerad.cinema.model.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonManager {
    @Autowired
    PersonneDao personDao;

    public List<Personne> getAll(){
        return personDao.findAllByOrderBySurname();
    }
}
