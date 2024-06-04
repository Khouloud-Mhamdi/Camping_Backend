package com.example.spring.camping.respositories.boutique;


import com.example.spring.camping.models.boutique.Commande;
import com.example.spring.camping.models.boutique.LigneDeCommande;
import com.example.spring.camping.models.boutique.LigneDeCommandeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneDeCommandeRepository extends JpaRepository<LigneDeCommande, LigneDeCommandeId> {
    List<LigneDeCommande> findAllByIdLigneDeCommandeCommande(Commande idLigneDeCommande_commande);
}
