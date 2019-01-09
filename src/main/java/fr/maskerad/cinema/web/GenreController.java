package fr.maskerad.cinema.web;
/*

import fr.maskerad.cinema.dao.GenreDao;
import fr.maskerad.cinema.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/genre")
public class GenreController {

    @Autowired
    GenreDao genreDao;

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("genres", genreDao.findAll());
        return "genre/list";
    }

    // ------------------- ADD/MOD ---------------------------------

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id){
        genreDao.deleteById(id);
        return "redirect:/genre/list";
    }

    @GetMapping("/mod/{id}")
    public String mod(@PathVariable("id")long id, Model model){
        Genre g = genreDao.findById(id).get();
        System.out.println(g);
        model.addAttribute("genre", g);
        return "genre/form";
    }

    @GetMapping("/add")
    public String add(Model model){
        Genre genre = new Genre();
        model.addAttribute("genre",genre);
        return "genre/form";
    }

    @PostMapping("/add")
    public String submit(@ModelAttribute Genre genre){
        genreDao.save(genre);
        return "redirect:/genre/list";
    }

}
*/

import fr.maskerad.cinema.model.Genre;
import fr.maskerad.cinema.service.GenreManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Controller
@RequestMapping("/genre")
public class GenreController {

    /**
     * Connexion au service de gestion du Genre des films
     */
    GenreManager genreManager;

    /**
     * Constructeur
     * @param genreManager
     */
    public GenreController(GenreManager genreManager){
        this.genreManager = genreManager;
        assert(genreManager != null);
    }

    /**
     * Page de gestion des genres
     * @param model
     * @return
     */
    @GetMapping("")
    public String main(Model model){
        model.addAttribute("genres", genreManager.getAll());
        model.addAttribute("newgenre", new Genre());
        return "genre/form";
    }

}
