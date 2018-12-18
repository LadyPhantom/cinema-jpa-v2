package fr.maskerad.cinema.web;

import fr.maskerad.cinema.dao.FilmsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ImageController {

    @Autowired
    FilmsDao filmsDao;

    @GetMapping(value = "/images/affiches/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImageAffiche(HttpServletResponse response, @PathVariable("id") String id) throws IOException {

        ClassPathResource imgFile;

        imgFile = new ClassPathResource("images/affiches/"
                + filmsDao.findById( Long.parseLong(id) ).get().getAffiche() );

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());

    }

    @GetMapping(value = "/images/personnes/{photoPath}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImagePersonne(HttpServletResponse response, @PathVariable("photoPath") String photoPath) throws IOException {

        ClassPathResource imgFile;

        imgFile = new ClassPathResource("images/personnes/"
                + photoPath );

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());

    }

}
