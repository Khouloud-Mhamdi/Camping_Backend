package com.example.spring.camping.controllers.boutique;


import com.example.spring.camping.models.boutique.Panier;
import com.example.spring.camping.services.boutique.IPanierService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/panier")
public class PanierController {

    IPanierService panierService;

    @GetMapping("/retrieve-all-paniers")
    public List<Panier> getPaniers() {
        List<Panier> listPaniers = panierService.retrieveAllPanier();
        return listPaniers;

    }

    @GetMapping("/retrieve-paniers/{paniers-id}")
    public Panier retrievePanier(@PathVariable("paniers-id") Long paniersId) {
        return panierService.retrievePanier(paniersId);
    }
    @PostMapping("/add-paniers")
    public void addPanier(@RequestBody Panier e) throws Exception {
        panierService.addPanier(e);
    }
    @PutMapping("/update-paniers")
    public Panier updatePanier(@RequestBody Panier e) throws Exception {
        Panier paniers = panierService.updatePanier(e);
        return paniers;
    }
    @DeleteMapping("/delete-paniers/{id}")
    public Panier deletePanier(@PathVariable("id") Long id)  {
        Panier paniers = panierService.deletePanierbyProductId(id);
        return paniers;
    }
}
