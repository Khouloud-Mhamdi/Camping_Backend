package com.example.spring.camping.controllers.boutique;

import com.example.spring.camping.models.boutique.Commande;
import com.example.spring.camping.services.boutique.ICommandeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/commande")
public class CommandeController {

    ICommandeService commandeService;

    @GetMapping("/retrieve-all-commandes")
    public List<Commande> getCommandes() {
        List<Commande> listCommandes = commandeService.retrieveAllCommandes();
        return listCommandes;

    }

    @GetMapping("/retrieve-commandes/{commandes-id}")
    public Commande retrieveCommande(@PathVariable("commandes-id") Long commandesId) {
        return commandeService.retrieveCommande(commandesId);
    }

    @PostMapping("/add-commandes-ligneDeCommande")
    public Commande addCommande(@RequestBody Commande e) {
        return commandeService.addCommande(e);
    }

  @PostMapping("/add-SingleRentedcommandes/{id}")
    public Commande addCommande(@RequestBody Commande e,@PathVariable("id")Long id) {
        return commandeService.addSingleRentedCommande(e,id);
    }



    @PutMapping("/update-commandes")
    public Commande updateCommande(@RequestBody Commande e){
        Commande commandes = commandeService.updateCommande(e);
        return commandes;
    }
}
