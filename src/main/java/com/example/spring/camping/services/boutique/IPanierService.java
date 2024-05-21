package com.example.spring.camping.services.boutique;


import com.example.spring.camping.models.boutique.Panier;

import java.util.List;

public interface IPanierService {
    List<Panier> retrieveAllPanier();
    void addPanier(Panier f) throws Exception;
    Panier updatePanier(Panier f) throws Exception;
    Panier retrievePanier(Long idPanier);

    Panier deletePanierbyProductId(Long idProduit);
}
