package fr.maskerad.cinema.web;

import fr.maskerad.cinema.dao.FilmsDao;
import fr.maskerad.cinema.dao.PersonneDao;
import fr.maskerad.cinema.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@PropertySource("classpath:config.properties")

@Controller
public class MainController {

    @Autowired
    PersonneDao personnesDao;

    @Autowired
    FilmsDao filmsDao;

    RoleDao roleDao;

    String imgPath;

    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("films", filmsDao.getAll());
        return "index";
    }

    @GetMapping("/film/{id}")//nom_mapping (chemin) différent du nom_template
    public String detail(Model model, @PathVariable("id") String id){
        Integer idFilm = Integer.parseInt(id);
        model.addAttribute("film", filmsDao.getById(idFilm) );
        return "detail";
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

    @GetMapping(value = "/images/affiches/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImageAffiche(HttpServletResponse response, @PathVariable("id") String id) throws IOException {

        ClassPathResource imgFile;

        imgFile = new ClassPathResource("images/affiches/"
                    + filmsDao.getById( Integer.parseInt(id) ).getAffiche() );
//        imgFile = new ClassPathResource(afficheImage.getAffiche() + filmsDao.getById( Integer.parseInt(id) ).getAffiche());

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());

    }

    @GetMapping(value = "/images/personnes/{photoPath}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImagePersonne(HttpServletResponse response, @PathVariable("photoPath") String photoPath) throws IOException {

        ClassPathResource imgFile;

        imgFile = new ClassPathResource("images/personnes/"
                    + photoPath );
//        imgFile = new ClassPathResource(afficheImage.getPersonne() + filmsDao.getById( Integer.parseInt(id) ).getRealisateur().getPhotoPath() );

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());

    }

//    @PostConstruct
//    public void init(){
//        roleDao = new RoleDao(personnesDao.personnes(), filmsDao.films());
//    }

}
