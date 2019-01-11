package fr.maskerad.cinema.web;

import fr.maskerad.cinema.dao.FilmsDao;
import fr.maskerad.cinema.dao.PersonneDao;
import fr.maskerad.cinema.model.Film;
import fr.maskerad.cinema.model.Personne;
import fr.maskerad.cinema.model.Role;
import fr.maskerad.cinema.service.FilmManager;
import fr.maskerad.cinema.service.GenreManager;
import fr.maskerad.cinema.service.ImageManager;

import fr.maskerad.cinema.service.PersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/film")
public class FilmController {

    @Autowired
    FilmManager filmManager;
//
//    @Autowired
//    FilmsDao filmsDao;
//
//    @Autowired
//    PersonneDao personneDao;


    @Autowired
    PersonManager personManager;

    @Autowired
    GenreManager genreManager;


    @Autowired
    ImageManager imm;

    @GetMapping("/list")
    public String list(Model model){
        Iterable<Film> films = filmManager.getAll();
        model.addAttribute("films", films );
        return "film/list";
    }

    @GetMapping("/{id}")//nom_mapping (chemin) différent du nom_template
    public String detail(Model model, @PathVariable("id") String id){
        Long idFilm = Long.parseLong(id);
        model.addAttribute("film", filmManager.getById(idFilm) );
        return "film/detail";
    }

    // ------------------- ADD/MOD ---------------------------------

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id){
//        filmsDao.deleteById(id);
        return "redirect:/film/list";
    }

    @GetMapping("/mod/{id}")
    public String mod(@PathVariable("id")long id, Model model){

//        Iterable<Personne> personnes = personManager.getAll();

//        model.addAttribute("film", filmManager.getById(id));
//        model.addAttribute("personnes", personnes );

        Film film = filmManager.getById(id);
        model.addAttribute("title", film.getTitle() + " : modification");
        model.addAttribute("persons", personManager.getAll());
        model.addAttribute("genresFilm", genreManager.getAll());
        model.addAttribute("film", film);
        model.addAttribute("plays", film.getRoles());
        model.addAttribute("newrole", new Role());

        return "film/modify";
    }
//
//    @GetMapping("/mod/{id}")
//    public String mod(@PathVariable("id")long id, Model model){
//        model.addAttribute("film", filmsDao.findById(id).get());
//        return "film/form";
//    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("film", new Film());
        return "film/form";
    }
/*

    @PostMapping("/add")
    public String submit(@RequestParam("poster") MultipartFile file, @ModelAttribute Film film){
        if(file.getContentType().equalsIgnoreCase("image/jpeg")){
            try {
                imm.savePoster(film, file.getInputStream());
            } catch (IOException ioe){
                System.out.println("Erreur lecture : "+ioe.getMessage());
            }
        }
        filmManager.save(film);

        return "redirect:/film/list";
    }
*/


    @PostMapping("/add")
    public String submit(@ModelAttribute Film film) {
        filmManager.save(film);
        return "redirect:list";
    }

    @GetMapping("/rmrole/{role_id}")
    public String rmRole(@PathVariable("role_id") Long roleId) {
        long filmId = filmManager.removeRole(roleId);

        return "redirect:/film/mod/" + filmId;
    }

    @PostMapping("/addrole")
    public String addRole(@ModelAttribute Role role) {
        long filmId = role.getFilm().getId();
        filmManager.addRole(filmId, role);
        return "redirect:/film/mod/" + filmId;
    }

    @PostMapping("/modrole/{id}")
    public String modRole(@PathVariable("id") long roleId, @ModelAttribute Role role) {
        filmManager.saveRole(role);
        return "redirect:/film/mod/" + role.getFilm().getId();
    }


}
