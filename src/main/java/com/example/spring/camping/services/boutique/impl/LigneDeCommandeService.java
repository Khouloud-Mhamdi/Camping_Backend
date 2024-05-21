package com.example.spring.camping.services.boutique.impl;


import com.example.spring.camping.models.boutique.LigneDeCommande;
import com.example.spring.camping.models.boutique.LigneDeCommandeId;
import com.example.spring.camping.respositories.boutique.LigneDeCommandeRepository;
import com.example.spring.camping.services.boutique.ILigneDeCommandeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LigneDeCommandeService implements ILigneDeCommandeService {
    LigneDeCommandeRepository commandeRepository;
    @Override
    public List<LigneDeCommande> retrieveAllLigneDeCommande() {
        return commandeRepository.findAll();
    }

    @Override
    public LigneDeCommande addLigneDeCommande(LigneDeCommande f) {
        return commandeRepository.save(f);
    }

    @Override
    public LigneDeCommande updateLigneDeCommande(LigneDeCommande f) {
        return commandeRepository.save(f);
    }

    @Override
    public LigneDeCommande retrieveLigneDeCommande(LigneDeCommandeId idLigneDeCommande) {
        return commandeRepository.findById(idLigneDeCommande).orElse(null);
    }
}
