package com.example.spring.camping.controllers.CampSiteControllers;


import com.example.spring.camping.models.CampLocations.DetailCampSite;
import com.example.spring.camping.models.CampLocations.Photo;
import com.example.spring.camping.respositories.CampSiteRepositories.PhotoRepository;
import com.example.spring.camping.servicesImpl.CampLocationsService.PhotoServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;

@RequestMapping("/photos")

@RestController
@AllArgsConstructor
public class PhotoController {


    PhotoServiceImpl photoService;
    PhotoRepository photoRepository;
    @PostMapping("/add")
    public ResponseEntity<Photo> addPhoto(@RequestBody byte[] newPhoto) {
        Photo savedPhoto = photoService.add(newPhoto);
        return ResponseEntity.ok().body(savedPhoto);
    }



    @GetMapping("/get/{id}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable Long id) throws IOException {
        byte[] photo = photoRepository.findById(id).orElseThrow().getImage();
        String contentType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(photo));
        if (contentType == null) {
            contentType = MediaType.IMAGE_JPEG_VALUE;
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(photo);
    }



    @PutMapping("/changephoto/{matricule}")
    public ResponseEntity<byte[]> putEmployeePhoto(@PathVariable Long matricule, @RequestBody byte[] newPhoto) throws IOException {
        // Retrieve the old photo from the data repository
        Photo photo = photoRepository.findById(matricule).orElseThrow();
        byte[] oldPhoto = photo.getImage();

        // Update the employee's photo with the new photo
        photo.setImage(newPhoto);
        photoRepository.save(photo);

        // Determine the content type of the updated photo
        String contentType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(newPhoto));
        if (contentType == null) {
            contentType = MediaType.IMAGE_JPEG_VALUE;
        }

        // Return the updated photo as the response
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(newPhoto);
    }






    @DeleteMapping("Supprimer/{id}")
    public void SupprimerPhoto(@PathVariable Long id) {
        photoService.delete(id);
    }

}
