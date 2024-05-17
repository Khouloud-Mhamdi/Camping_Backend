package com.example.spring.camping.controllers.boutique;


import com.example.spring.camping.models.boutique.LigneDeCommande;
import com.example.spring.camping.models.boutique.LigneDeCommandeId;
import com.example.spring.camping.services.boutique.ILigneDeCommandeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/ligneDeCommande")
public class LigneDeCommandeController {

    ILigneDeCommandeService ligneDeCommande;

    @GetMapping("/retrieve-all-ligneDeCommandes")
    public List<LigneDeCommande> getLigneDeCommandes() {
        List<LigneDeCommande> listLigneDeCommandes = ligneDeCommande.retrieveAllLigneDeCommande();
        return listLigneDeCommandes;

    }

    @GetMapping("/retrieve-ligneDeCommandes/{ligneDeCommandes-id}")
    public LigneDeCommande retrieveLigneDeCommande(@PathVariable("ligneDeCommandes-id") LigneDeCommandeId ligneDeCommandesId) {
        return ligneDeCommande.retrieveLigneDeCommande(ligneDeCommandesId);
    }

    @PostMapping("/add-ligneDeCommandes")
    public LigneDeCommande addLigneDeCommande(@RequestBody LigneDeCommande e) {
        LigneDeCommande ligneDeCommandes = ligneDeCommande.addLigneDeCommande(e);
        return ligneDeCommandes;
    }



    @PutMapping("/update-ligneDeCommandes")
    public LigneDeCommande updateLigneDeCommande(@RequestBody LigneDeCommande e){
        LigneDeCommande ligneDeCommandes = ligneDeCommande.updateLigneDeCommande(e);
        return ligneDeCommandes;
    }
}
