package fr.maskerad.cinema.dao;

import fr.maskerad.cinema.model.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreDao extends CrudRepository<Genre, Long> {
}
