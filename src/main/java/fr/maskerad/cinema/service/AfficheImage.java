package fr.maskerad.cinema.service;

import fr.maskerad.cinema.dao.FilmsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AfficheImage {

    @Autowired
    FilmsDao filmsDao;


//    public ResponseEntity<byte[]> getImage(String id) throws IOException {
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

    public void getImage(
            HttpServletResponse response,
            @RequestParam("id") String id) throws IOException {

        ClassPathResource imgFile = new ClassPathResource("affiches/"
                + filmsDao.getById(Integer.parseInt(id)).getAfficheNom());

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
    }
}

