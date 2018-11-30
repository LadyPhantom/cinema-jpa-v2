package fr.maskerad.cinema.web;

import fr.maskerad.cinema.dao.FilmsDao;
import fr.maskerad.cinema.service.AfficheImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@PropertySource("classpath:config.properties")

@Controller
public class MainController {

    @Autowired
    FilmsDao filmsDao;

    @Autowired
    AfficheImage img;

    String imgPath;

    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("nom", "Karl");
        model.addAttribute("films", filmsDao.films());
        return "index";
    }

//    @GetMapping("/film")//nom_mapping (chemin) différent du nom_template
//    public String detail(Model model, @RequestParam("id") String id){
//        Integer idFilm = Integer.parseInt(id);
//        model.addAttribute("film", filmsDao.getById(idFilm));
//        return "detail"; //template
//    }

    @GetMapping("/film/{id}")//nom_mapping (chemin) différent du nom_template
    public String detail(Model model, @PathVariable("id") String id){
        Integer idFilm = Integer.parseInt(id);
        model.addAttribute("film", filmsDao.getById(idFilm));
        return "detail"; //template
    }

    // ------------------- 1ere méthode ---------------------------

//    @GetMapping(value = "/images", produces = MediaType.IMAGE_JPEG_VALUE)
//    public ResponseEntity<byte[]> getImage(@RequestParam("id") String id) throws IOException {
//
//        ClassPathResource imgFile = new ClassPathResource( "affiches/"
//                + filmsDao.getById( Integer.parseInt(id)).getAfficheNom() );
//
//          // transform inputStream (image) en bytes
//        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
//
//          // On écrit dans le corps (body) de la réponse http en précisant le type de contenu (image_jpeg)
//        return ResponseEntity
//                .ok()
//                .contentType(MediaType.IMAGE_JPEG)
//                .body(bytes);
//    }

    // ------------------- 2eme méthode ---------------------------
    // à déplacer dans service

//    @GetMapping(value = "/images", produces = MediaType.IMAGE_JPEG_VALUE)
//    public void getImage(HttpServletResponse response, @RequestParam("id") String id) throws IOException {
//
//        ClassPathResource imgFile = new ClassPathResource("affiches/"
//                + filmsDao.getById( Integer.parseInt(id)).getAfficheNom() );
//
//        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
//        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
//
//    }
}
