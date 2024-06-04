package com.example.spring.camping.controllers.boutique;

import com.example.spring.camping.models.TypeProduct;
import com.example.spring.camping.models.boutique.Commande;
import com.example.spring.camping.models.boutique.StatisticsMessage;
import com.example.spring.camping.services.boutique.ICommandeService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/commande")
public class CommandeController {

    ICommandeService commandeService;

    @GetMapping("/countProductBought")
    public long countProductBought() {
        List<Commande> listCommandes = commandeService.retrieveAllCommandes();
        return listCommandes.stream().filter((v)->v.getType_Commande()==TypeProduct.SELLABLE).count();

    }

    @GetMapping("/retrieve-all-commandes")
    public List<Commande> getCommandes() {
        List<Commande> listCommandes = commandeService.retrieveAllCommandes();
        return listCommandes;

    }

    @GetMapping("/countProductRented")
    public long countProductRented() {
        List<Commande> listCommandes = commandeService.retrieveAllCommandes();
        return listCommandes.stream().filter((v)->v.getType_Commande()==TypeProduct.RENTABLE).count();
    }

    @GetMapping("/getProfit")
    public float getProfit() {
        return commandeService.getProfit();
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

    @DeleteMapping("/delete-commandes/{idCommande}")
    public void deleteCommande(@PathVariable("idCommande") Long idCommande){
        commandeService.deleteCommande(idCommande);
    }
    @GetMapping("/countProductBoughtBetteweenDates/{dateDebut}/{dateFin}/{type}")
    public long countProductBoughtBetteweenDates(@PathVariable("dateDebut") Date dateDebut, @PathVariable("dateFin")  Date dateFin, @PathVariable("type")  TypeProduct type) {
        return         commandeService.countSalesBetweenDates(dateDebut,dateFin,type);
    }
    @GetMapping("/findTotalCommandeBetweenDates/{dateDebut}/{dateFin}/{type}")
    public Double findTotalCommandeBetweenDates(@PathVariable("dateDebut") Date dateDebut, @PathVariable("dateFin")  Date dateFin, @PathVariable("type")  TypeProduct type) {
        return commandeService.findTotalSalesBetweenDates(dateDebut, dateFin, type);
    }

    @MessageMapping("/update")
    @SendTo("/topic/statistics")
    public StatisticsMessage send(StatisticsMessage message) throws Exception {
        // Logique pour traiter les statistiques et renvoyer la mise à jour
        return message;
    }

    }
