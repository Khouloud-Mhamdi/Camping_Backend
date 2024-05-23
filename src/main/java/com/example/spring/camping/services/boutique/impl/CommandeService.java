package com.example.spring.camping.services.boutique.impl;


import com.example.spring.camping.models.EtatCommande;
import com.example.spring.camping.models.TypeProduct;
import com.example.spring.camping.models.boutique.Commande;
import com.example.spring.camping.models.boutique.LigneDeCommande;
import com.example.spring.camping.models.boutique.LigneDeCommandeId;
import com.example.spring.camping.models.boutique.Panier;
import com.example.spring.camping.respositories.boutique.CommandeRepository;
import com.example.spring.camping.respositories.boutique.LigneDeCommandeRepository;
import com.example.spring.camping.respositories.boutique.PanierRepository;
import com.example.spring.camping.services.boutique.ICommandeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CommandeService implements ICommandeService {
    CommandeRepository commandeRepository;
    PanierRepository panierRepository;
    LigneDeCommandeRepository ligneDeCommandeRepository;
    @Override
    public List<Commande> retrieveAllCommandes() {
        return commandeRepository.findAll();
    }

    @Override
    public Commande addCommande(Commande f) {
        Commande commande = commandeRepository.save(f);
        List<Panier> paniers = panierRepository.findAll().stream().filter((v)->v.getProduit().getProduct_Type()==f.getType_Commande()).collect(Collectors.toList());
        if(paniers!=null){
            paniers.forEach((v->{
                LigneDeCommande ligneDeCommande = new LigneDeCommande();
                LigneDeCommandeId ligneDeCommandeId = new LigneDeCommandeId(v.getProduit(),commande);
                ligneDeCommande.setId_LigneDeCommande(ligneDeCommandeId);
                ligneDeCommande.setQuantiter(v.getQuantiter());
                ligneDeCommande.setPrix_Unitaire(v.getProduit().getPrix());
                if(f.getType_Commande()== TypeProduct.SELLABLE)
                    ligneDeCommande.setEtat_LigneDeCommande(EtatCommande.SOLD);
                ligneDeCommandeRepository.save(ligneDeCommande);
                panierRepository.delete(v);
            }));
        }
        return commande;
    }

    @Override
    public Commande addSingleRentedCommande(Commande f,Long id) {
        Commande commande = commandeRepository.save(f);
        List<Panier> paniers = panierRepository.findAll().stream().filter((v)->v.getProduit().getIdProduct()==id).collect(Collectors.toList());
        if(paniers!=null){
            paniers.forEach((v->{
                LigneDeCommande ligneDeCommande = new LigneDeCommande();
                LigneDeCommandeId ligneDeCommandeId = new LigneDeCommandeId(v.getProduit(),commande);
                ligneDeCommande.setId_LigneDeCommande(ligneDeCommandeId);
                ligneDeCommande.setQuantiter(v.getQuantiter());
                ligneDeCommande.setPrix_Unitaire(v.getProduit().getPrix());
                if(f.getType_Commande()== TypeProduct.SELLABLE)
                    ligneDeCommande.setEtat_LigneDeCommande(EtatCommande.SOLD);
                else
                    ligneDeCommande.setEtat_LigneDeCommande(EtatCommande.RENTED);
                ligneDeCommandeRepository.save(ligneDeCommande);
                panierRepository.delete(v);
            }));
        }
        return commande;
    }

    @Override
    public Commande updateCommande(Commande f) {
        return commandeRepository.save(f);
    }

    @Override
    public Commande retrieveCommande(Long idCommande) {
        return commandeRepository.findById(idCommande).orElse(null);
    }

    @Scheduled(fixedDelay = 60000)
    void scheduler() {
        log.info("test");
    }
}
