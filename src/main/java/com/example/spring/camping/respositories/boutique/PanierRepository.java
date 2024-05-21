package com.example.spring.camping.respositories.boutique;


import com.example.spring.camping.models.boutique.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanierRepository extends JpaRepository<Panier,Long> {
     Panier findByProduitIdProduct(Long nomProduit);
}
