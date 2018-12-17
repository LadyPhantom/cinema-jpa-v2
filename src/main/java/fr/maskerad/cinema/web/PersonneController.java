package fr.maskerad.cinema.web;

import fr.maskerad.cinema.dao.PersonneDao;
import fr.maskerad.cinema.model.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping(value = "/person")
public class PersonneController {

    @Autowired
    PersonneDao personneDao;

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("personnes", personneDao.getAll());
        return "person/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") long id, Model model){
        model.addAttribute("personne", personneDao.getById(id));
        return "person/detail";
    }

    @GetMapping("/mod/{id}")
    public String mod(@PathVariable("id")long id, Model model){
        Personne p = personneDao.getById(id);
        System.out.println(p);
        model.addAttribute("personne", p);
        return "person/form";
    }

    @GetMapping("/add")
    public String add(Model model){
        Personne personne = new Personne();
        model.addAttribute("personne",personne);
        return "person/form";
    }

    @PostMapping("/add")
    public String submit(@RequestParam("photo") MultipartFile file , @ModelAttribute Personne personne){

//        if (file.getContentType().equalsIgnoreCase("image/jpeg")){
//            try{
//                imm.savePhoto(personne, file.getInputStream() );
//            }catch (IOException e){
//                System.out.println("Errerur lecture : " + e.getMessage());
//            }
//        }

        personneDao.save(personne);
        return "redirect:/person/list";
    }

}
