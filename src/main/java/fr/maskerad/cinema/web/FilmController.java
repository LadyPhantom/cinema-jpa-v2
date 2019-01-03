package fr.maskerad.cinema.web;

import fr.maskerad.cinema.dao.FilmsDao;
import fr.maskerad.cinema.dao.PersonneDao;
import fr.maskerad.cinema.model.Film;
import fr.maskerad.cinema.model.Personne;
import fr.maskerad.cinema.service.ImageManager;

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
    FilmsDao filmsDao;

    @Autowired
    PersonneDao personneDao;

    @Autowired
    ImageManager imm;

    @GetMapping("/list")
    public String list(Model model){
        Iterable<Film> films = filmsDao.findAll();
        model.addAttribute("films", films );
        return "film/list";
    }

    @GetMapping("/{id}")//nom_mapping (chemin) différent du nom_template
    public String detail(Model model, @PathVariable("id") String id){
        Long idFilm = Long.parseLong(id);
        model.addAttribute("film", filmsDao.findById(idFilm).get() );
        return "film/detail";
    }

    // ------------------- ADD/MOD ---------------------------------

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id){
        filmsDao.deleteById(id);
        return "redirect:/film/list";
    }

    @GetMapping("/mod/{id}")
    public String mod(@PathVariable("id")long id, Model model){

        Iterable<Personne> personnes = personneDao.findAll();

        model.addAttribute("film", filmsDao.findById(id).get());
        model.addAttribute("personnes", personnes );
        
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

    @PostMapping("/add")
    public String submit(@RequestParam("poster") MultipartFile file, @ModelAttribute Film film){
        if(file.getContentType().equalsIgnoreCase("image/jpeg")){
            try {
                imm.savePoster(film, file.getInputStream());
            } catch (IOException ioe){
                System.out.println("Erreur lecture : "+ioe.getMessage());
            }
        }
        filmsDao.save(film);

        return "redirect:/film/list";
    }
}
