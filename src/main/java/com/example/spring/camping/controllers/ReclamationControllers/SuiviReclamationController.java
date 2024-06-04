package com.example.spring.camping.controllers.ReclamationControllers;

import com.example.spring.camping.models.Reclamation.SuiviReclamation;
import com.example.spring.camping.services.ReclamationServices.SuiviReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suiviReclamation")
public class SuiviReclamationController {

    @Autowired
    private SuiviReclamationService suiviReclamationService;

    @PostMapping("/add-suivireclamation")
    public ResponseEntity<SuiviReclamation> createSuiviReclamation(@RequestBody SuiviReclamation suiviReclamation) {
        System.out.println(suiviReclamation.getByWho());

        SuiviReclamation newSuiviReclamation = suiviReclamationService.saveSuiviReclamation(suiviReclamation);

        return ResponseEntity.ok(newSuiviReclamation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuiviReclamation> updateSuiviReclamation(
            @PathVariable long id,
            @RequestBody SuiviReclamation suiviReclamationDetails) {
        SuiviReclamation suiviReclamation = suiviReclamationService.getSuiviReclamationById(id);
        if (suiviReclamation == null) {
            return ResponseEntity.notFound().build();
        }

        suiviReclamation.setIdReclamation(suiviReclamationDetails.getIdReclamation());
        suiviReclamation.setDateSuiviRec(suiviReclamationDetails.getDateSuiviRec());
        suiviReclamation.setDescSuivi(suiviReclamationDetails.getDescSuivi());

        SuiviReclamation updatedSuiviReclamation = suiviReclamationService.updateSuiviReclamation(suiviReclamation);
        return ResponseEntity.ok(updatedSuiviReclamation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSuiviReclamation(@PathVariable long id) {
        SuiviReclamation suiviReclamation = suiviReclamationService.getSuiviReclamationById(id);
        if (suiviReclamation == null) {
            return ResponseEntity.notFound().build();
        }

        suiviReclamationService.deleteSuiviReclamation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuiviReclamation> getSuiviReclamationById(@PathVariable long id) {
        SuiviReclamation suiviReclamation = suiviReclamationService.getSuiviReclamationById(id);
        if (suiviReclamation == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(suiviReclamation);
    }

    @GetMapping("/read")
    public ResponseEntity<List<SuiviReclamation>> getAllSuiviReclamations() {
        List<SuiviReclamation> suiviReclamations = suiviReclamationService.getAllSuiviReclamations();
        return ResponseEntity.ok(suiviReclamations);
    }
}
