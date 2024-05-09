package com.example.spring.camping.controllers;

import com.example.spring.camping.models.Reclamation;
import com.example.spring.camping.services.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reclamations")
public class ReclamationController {

    @Autowired
    private ReclamationService reclamationService;

    @PostMapping("/add")
    public ResponseEntity<Reclamation> createReclamation(@RequestBody Reclamation reclamation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reclamationService.saveReclamation(reclamation));
    }

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

    @GetMapping("/statistics/reclamation-type")
    public ResponseEntity<Map<String, Float>> getStatisticsByReclamationType() {
        Map<String, Float> statistics = reclamationService.getStatisticsByReclamationType();
        return ResponseEntity.ok().body(statistics);
    }

    @GetMapping("/statistics/reclamations-count-by-month")
    public ResponseEntity<Map<String, Integer>> getReclamationsCountByMonth() {
        Map<String, Integer> statistics = reclamationService.getReclamationsCountByMonth();
        return ResponseEntity.ok().body(statistics);
    }

    @GetMapping("/client/{idClient}") // Change the path variable name to match the method parameter
    public ResponseEntity<List<Reclamation>> getReclamationsByClientId(@PathVariable long idClient) { // Change the path variable name
        List<Reclamation> reclamations = reclamationService.getReclamationsByClientId(idClient);
        return ResponseEntity.ok().body(reclamations);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Reclamation>> getReclamationsByStatus(@PathVariable String status) {
        List<Reclamation> reclamations = reclamationService.getReclamationsByStatus(status);
        return ResponseEntity.ok().body(reclamations);
    }

    @GetMapping("/statistics/enattente-count")
    public ResponseEntity<Integer> getEnAttenteReclamationNumber() {
        int count = reclamationService.getEnAttenteReclamationNumber();
        System.out.println(count);

        return ResponseEntity.ok().body(count);
    }

    @GetMapping("/statistics/solved-this-month-count")
    public ResponseEntity<Integer> getSolvedReclamationNumberThisMonth() {
        int count = reclamationService.getSolvedReclamationNumberThisMonth();
        return ResponseEntity.ok().body(count);
    }

    @GetMapping("/statistics/solved-last-month-count")
    public ResponseEntity<Integer> getSolvedReclamationNumberLastMonth() {
        int count = reclamationService.getSolvedReclamationNumberLastMonth();
        return ResponseEntity.ok().body(count);
    }
}
