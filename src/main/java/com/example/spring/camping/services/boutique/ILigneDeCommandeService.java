package com.example.spring.camping.services.boutique;


import com.example.spring.camping.models.boutique.LigneDeCommande;
import com.example.spring.camping.models.boutique.LigneDeCommandeId;

import java.util.List;

public interface ILigneDeCommandeService {
    List<LigneDeCommande> retrieveAllLigneDeCommande();
    LigneDeCommande addLigneDeCommande(LigneDeCommande f);
    LigneDeCommande updateLigneDeCommande(LigneDeCommande f);
    LigneDeCommande retrieveLigneDeCommande(LigneDeCommandeId idLigneDeCommande);
}
