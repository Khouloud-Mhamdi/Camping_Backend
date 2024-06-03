package com.example.spring.camping.controllers;


import com.example.spring.camping.models.ManageUsers.DetailsUser;
import com.example.spring.camping.models.boutique.Product;
import com.example.spring.camping.servicesImpl.DetailsUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/detailsUser")
    @CrossOrigin("*")
    @RestController
    public class DetailsUserController {
        @Autowired
        private DetailsUserServiceImpl detailsUserService;
        @GetMapping("/Consulter")
        public List<DetailsUser> ListerUtilisateurs() {
            return detailsUserService.getAll();
        }

        @GetMapping("Rechercher/{id}")
        public DetailsUser ConsulterUtilisateur(@PathVariable Long id) {
            return detailsUserService.getById(id);
        }

        @PutMapping("Modifier/{id}")
        public DetailsUser  ModifierUtilisateur(@RequestBody DetailsUser detailsUser) {
            return detailsUserService.update(detailsUser);
        }

        @DeleteMapping("Supprimer/{id}")
        public void SupprimerUtilisateur(@PathVariable Long id) {
            detailsUserService.delete(id);
        }

    @PostMapping("/Add-DetailsUser/{email}")
    public  void AffecterDetailsUser ( @RequestBody DetailsUser detailsUser,@PathVariable String email ){
        detailsUserService.addDetailUser(detailsUser,email);
    }

    @GetMapping("/favorite-seasons")
    public ResponseEntity<List<Object[]>> getFavoriteSeasonStatistics() {
        List<Object[]> favoriteSeasonStatistics = detailsUserService.getFavoriteSeasonStatistics();
        return ResponseEntity.ok(favoriteSeasonStatistics);
    }
    @GetMapping("/favorite-landscapes")
    public ResponseEntity<List<Object[]>> getFavoriteLandscapeStatistics() {
        List<Object[]> favoriteLandscapeStatistics = detailsUserService.getFavoriteLandscapeStatistics();
        return ResponseEntity.ok(favoriteLandscapeStatistics);
    }
    @GetMapping("/favorite-accommodation-types")
    public ResponseEntity<List<Object[]>> getFavoriteAccommodationTypeStatistics() {
        List<Object[]> favoriteAccommodationTypeStatistics = detailsUserService.getFavoriteAccommodationTypeStatistics();
        return ResponseEntity.ok(favoriteAccommodationTypeStatistics);
    }

    @GetMapping("/recommenderProduits/{userId}")
    public List<Product> getRecommendations(@PathVariable Long userId) {
        List<Product> recommendedProducts = detailsUserService.recommendProducts(userId);
        return recommendedProducts;
    }



    }
