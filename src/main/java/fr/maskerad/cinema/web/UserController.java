package fr.maskerad.cinema.web;

import fr.maskerad.cinema.dao.UserDao;
import fr.maskerad.cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDao userDao;

    @GetMapping("/list")
    public String list(Model model){

        try{
            Iterable<User> users = userDao.findAll();
            model.addAttribute("users", users );
            return "user/list";
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "redirect:localhost:8080";
        }

    }

    @GetMapping("/{id}")
    public String detail(Model model, @PathVariable("id") String id){
        Long idUser = Long.parseLong(id);
        model.addAttribute("user", userDao.findById(idUser).get() );
        return "user/detail";
    }

    // ------------------- ADD/MOD ---------------------------------

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id){
        userDao.deleteById(id);
        return "redirect:/user/list";
    }

    @GetMapping("/mod/{id}")
    public String mod(@PathVariable("id")long id, Model model){
        model.addAttribute("user", userDao.findById(id).get());
        return "user/form";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("user", new User());
        return "user/form";
    }

    @PostMapping("/add")
    public String submit(@ModelAttribute User user) {
        userDao.save(user);
        return "redirect:/user/list";
    }

}
