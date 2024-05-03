package com.example.spring.camping.controllers;
import com.example.spring.camping.models.Reclamation;
import com.example.spring.camping.services.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reclamations")
public class ReclamationController {

    @Autowired
    private ReclamationService reclamationService;

    // Create
    @PostMapping("/add")
    public ResponseEntity<Reclamation> createReclamation(@RequestBody Reclamation reclamation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reclamationService.saveReclamation(reclamation));
    }

    // Read
    @GetMapping("/read")
    public ResponseEntity<List<Reclamation>> getAllReclamations() {
        return ResponseEntity.ok().body(reclamationService.getAllReclamations());
    }

    @GetMapping("/reclamation/{id}")
    public ResponseEntity<Reclamation> getReclamationById(@PathVariable long id) {
        Reclamation reclamation = reclamationService.getReclamationById(id);
        if (reclamation != null) {
            return ResponseEntity.ok().body(reclamation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update
    @PutMapping("/updatereclamation/{id}")
    public ResponseEntity<Reclamation> updateReclamation(@PathVariable long id, @RequestBody Reclamation reclamationDetails) {
        Reclamation reclamation = reclamationService.getReclamationById(id);
        if (reclamation != null) {
            reclamation.setIdClient(reclamationDetails.getIdClient());
            reclamation.setDateReclamation(reclamationDetails.getDateReclamation());
            reclamation.setDescriptionReclamation(reclamationDetails.getDescriptionReclamation());
            reclamation.setStatutReclamation(reclamationDetails.getStatutReclamation());
            reclamation.setDateResolution(reclamationDetails.getDateResolution());
            reclamation.setReponse(reclamationDetails.getReponse());

            return ResponseEntity.ok().body(reclamationService.updateReclamation(reclamation));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReclamation(@PathVariable long id) {
        Reclamation reclamation = reclamationService.getReclamationById(id);
        if (reclamation != null) {
            reclamationService.deleteReclamation(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}