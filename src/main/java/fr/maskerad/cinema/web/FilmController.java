package fr.maskerad.cinema.web;

import fr.maskerad.cinema.dao.FilmsDao;
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
    ImageManager imm;

//    @GetMapping("/")
//    public String main(Model model){
//        model.addAttribute("films", filmsDao.getAll());
//        return "index";
//    }

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

    @GetMapping("/mod/{id}")
    public String mod(@PathVariable("id")long id, Model model){
        model.addAttribute("film", filmsDao.findById(id).get());
        return "film/form";
    }

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

//    @PostConstruct
//    public void init(){
//        roleDao = new RoleDao(personnesDao.personnes(), filmsDao.films());
//    }

}
