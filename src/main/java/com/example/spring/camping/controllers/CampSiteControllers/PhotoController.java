package com.example.spring.camping.controllers.CampSiteControllers;


import com.example.spring.camping.models.CampLocations.DetailCampSite;
import com.example.spring.camping.models.CampLocations.Photo;
import com.example.spring.camping.models.ManageUsers.User;
import com.example.spring.camping.models.boutique.Product;
import com.example.spring.camping.respositories.CampSiteRepositories.DetailCampSiteRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.PhotoRepository;
import com.example.spring.camping.respositories.UserRepository;
import com.example.spring.camping.respositories.boutique.ProductRepository;
import com.example.spring.camping.servicesImpl.CampLocationsService.CloudinaryService;
import com.example.spring.camping.servicesImpl.CampLocationsService.PhotoServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.*;
import java.util.List;

@RequestMapping("/photos")

@RestController
@AllArgsConstructor
public class PhotoController {


    PhotoServiceImpl photoService;
    PhotoRepository photoRepository;
    DetailCampSiteRepository detailCampSiteRepository;
    CloudinaryService cloudinaryService;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @PostMapping("/add/{id_detail}")
    public ResponseEntity<Photo> addPhoto(@PathVariable Long id_detail,@RequestBody byte[] newPhoto) {
        Photo savedPhoto = photoService.add(newPhoto,id_detail);
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
    @GetMapping("/photos/detailcampsite/{id}")
    public List<byte[]> getPhotosByDetailCampsite(@PathVariable Long id) throws IOException {
        return photoService.getAllByDetailCampsite(id);

    }

    @DeleteMapping("Supprimer/{id}")
    public void SupprimerPhoto(@PathVariable Long id) {
        photoService.delete(id);
    }

//cloudinary part
    @GetMapping("/list/{idDetailCamp}")
    public ResponseEntity<List<String>>list(@PathVariable Long idDetailCamp){
        List<String> list=photoService.list(idDetailCamp);
        return new ResponseEntity<>(list, org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_OK));
    }
    //add to detailcampsite
    @PostMapping("/upload/{descriptionDetail}")
    public ResponseEntity<String>uploadEtAffecterDetailCampsite(@RequestParam MultipartFile multipartFile,@PathVariable String  descriptionDetail) throws IOException{
        BufferedImage bi= ImageIO.read(multipartFile.getInputStream());
        if (bi==null){
            return new ResponseEntity<>("image non valide", org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_BAD_REQUEST));
        }
        Map result=cloudinaryService.upload(multipartFile);
        Photo image=new Photo((String) result.get("original_filename"),
                (String) result.get("url"),
                (String) result.get("public_id"));
        DetailCampSite detailCampSite=detailCampSiteRepository.findByDescription(descriptionDetail);
        image.setDetailCampSites(detailCampSite);
        photoService.save(image);
        return new ResponseEntity<>("image ajoutee avec succes", org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_OK));
    }
//add to produit
    @PostMapping("/uploadToproduct/{idProduit}")
    public ResponseEntity<String>uploadEtAffecterProduit(@RequestParam MultipartFile multipartFile,@PathVariable Long  idProduit) throws IOException{
        BufferedImage bi= ImageIO.read(multipartFile.getInputStream());
        if (bi==null){
            return new ResponseEntity<>("image non valide", org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_BAD_REQUEST));
        }
        Map result=cloudinaryService.upload(multipartFile);
        Photo image=new Photo((String) result.get("original_filename"),
                (String) result.get("url"),
                (String) result.get("public_id"));
        Product produit = productRepository.getById(idProduit);
        image.setProduct(produit);
        photoService.save(image);
        return new ResponseEntity<>("image ajoutee avec succes", org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_OK));
    }
    //add to user
    @PostMapping("/uploadTouser/{email}")
    public ResponseEntity<String>uploadEtAffecterUser(@RequestParam MultipartFile multipartFile,@PathVariable String email ) throws IOException{
        BufferedImage bi= ImageIO.read(multipartFile.getInputStream());
        if (bi==null){
            return new ResponseEntity<>("image non valide", org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_BAD_REQUEST));
        }
        Map result=cloudinaryService.upload(multipartFile);
        Photo image=new Photo((String) result.get("original_filename"),
                (String) result.get("url"),
                (String) result.get("public_id"));
        Optional<User> user = userRepository.findByEmail(email);
       image.setUser(user.get());
        photoService.save(image);
        return new ResponseEntity<>("image ajoutee avec succes", org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_OK));
    }

//Basic add
    @PostMapping("/upload")
    public ResponseEntity<String>upload(@RequestParam MultipartFile multipartFile) throws IOException{
        BufferedImage bi= ImageIO.read(multipartFile.getInputStream());
        if (bi==null){
            return new ResponseEntity<>("image non valide", org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_BAD_REQUEST));
        }
        Map result=cloudinaryService.upload(multipartFile);
        Photo image=new Photo((String) result.get("original_filename"),
                (String) result.get("url"),
                (String) result.get("public_id"));
        photoService.save(image);
        return new ResponseEntity<>("image ajoutee avec succes", org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_OK));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        Optional<Photo> imageOptional=photoService.getOne(id);
        if(imageOptional.isEmpty()){
            return new ResponseEntity<>("nexiste pas", org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_NOT_FOUND));
        }
        Photo imagePh=imageOptional.get();
        String cloudinaryID=imagePh.getImageId();
        try {
            cloudinaryService.delete(cloudinaryID);
        }catch (IOException e) {
            return new ResponseEntity<>("Failed to delete image from cloudinary", org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        }
        photoService.delete(id);
        return new  ResponseEntity<>("image supprimee", org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_OK)) ;
    }



}
