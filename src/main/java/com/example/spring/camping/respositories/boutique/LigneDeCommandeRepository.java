package com.example.spring.camping.respositories.boutique;


import com.example.spring.camping.models.boutique.LigneDeCommande;
import com.example.spring.camping.models.boutique.LigneDeCommandeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneDeCommandeRepository extends JpaRepository<LigneDeCommande, LigneDeCommandeId> {
}
