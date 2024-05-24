package com.example.spring.camping.controllers.ReclamationControllers;

import com.example.spring.camping.models.Reclamation.EStatusReclamation;
import com.example.spring.camping.models.Reclamation.Reclamation;
import com.example.spring.camping.services.ReclamationServices.ReclamationService;
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


    //CRUD

    @PostMapping("/add")
    public ResponseEntity<Reclamation> createReclamation(@RequestBody Reclamation reclamation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reclamationService.saveReclamation(reclamation));
    }


    @GetMapping("/read")
    public ResponseEntity<List<Reclamation>> getAllReclamations() {
        return ResponseEntity.ok().body(reclamationService.getAllReclamations());
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

    @DeleteMapping("/archive/{id}")
    public ResponseEntity<Void> archivereclamation(@PathVariable long id) {
        Reclamation reclamation = reclamationService.getReclamationById(id);
        if (reclamation != null) {
            reclamationService.deleteReclamation(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }





    //getReclamationBy
    @GetMapping("/reclamation/{id}")
    public ResponseEntity<Reclamation> getReclamationById(@PathVariable long id) {
        Reclamation reclamation = reclamationService.getReclamationById(id);
        if (reclamation != null) {
            return ResponseEntity.ok().body(reclamation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/statistics/reclamation-type")
    public ResponseEntity<Map<EStatusReclamation, Float>> getStatisticsByReclamationType() {
        Map<EStatusReclamation, Float> statistics = reclamationService.getStatisticsByReclamationType();
        return ResponseEntity.ok().body(statistics);
    }


    @GetMapping("/statistics/reclamations-count-by-month")
    public ResponseEntity<Map<String, Integer>> getReclamationsCountByMonth() {
        Map<String, Integer> statistics = reclamationService.getReclamationsCountByMonth();
        return ResponseEntity.ok().body(statistics);
    }

    @GetMapping("/client/{idClient}")
    public ResponseEntity<List<Reclamation>> getReclamationsByClientId(@PathVariable long idClient) {
        List<Reclamation> reclamations = reclamationService.getReclamationsByClientId(idClient);
        return ResponseEntity.ok().body(reclamations);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Reclamation>> getReclamationsByStatus(@PathVariable EStatusReclamation status) {
        List<Reclamation> reclamations = reclamationService.getReclamationsByStatus(status);
        return ResponseEntity.ok().body(reclamations);
    }

    @GetMapping("/statistics/enattente-count")
    public ResponseEntity<Integer> getPendingReclamationNumber() {
        int count = reclamationService.getPendingReclamationNumber();
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
