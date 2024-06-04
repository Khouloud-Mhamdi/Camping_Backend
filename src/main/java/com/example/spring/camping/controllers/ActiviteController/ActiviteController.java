package com.example.spring.camping.controllers.ActiviteController;

import com.example.spring.camping.models.Activites.Activite;

import com.example.spring.camping.models.Activites.Pre_requis;
import com.example.spring.camping.models.Reservation.Reservation;
import com.example.spring.camping.servicesImpl.ActiviteServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/Activite")
@CrossOrigin(origins = "*")
public class ActiviteController {

    private ActiviteServiceImpl activiteService;

    @GetMapping("/RetrieveActivite")
    public List<Activite> RetrieveAll() {
        List<Activite> activite = activiteService.retrieveAll();
        return activite;
    }

    @GetMapping("/RetrieveActivite/{Activite-id}")
    public Optional<Activite> RetrieveById(@PathVariable("Activite-id") long id) {
        Optional<Activite> Activite = activiteService.retrieveActiviteById(id);
        return Activite;
    }

    @PostMapping("/AjoutActivite")
    public Activite Add(@RequestBody Activite a) {
        Activite activite = activiteService.ajoutActivite(a);
        return activite;
    }

    @PutMapping("/UpdateActivite")
    public Activite Update(@RequestBody Activite a) {
        Activite activite = activiteService.updateActivite(a);
        return activite;
    }

    @PutMapping("/UpdateActiviteById/{Activite-id}")
    public Activite UpdateActiviteById(@PathVariable("Activite-id") long id,@RequestBody Activite activite) {
        Activite act = activiteService.updateActiviteById(id, activite);
        return act;
    }

    @DeleteMapping("/DeleteActivite/{Activite-id}")
    public void Delete(@PathVariable("Activite-id") long id) {
        activiteService.delete(id);
    }

    @PutMapping("/ApprouverActivite/{Activite-id}")
    public Activite approver(@PathVariable("Activite-id") long id) {
        Activite act = activiteService.approuverActivite(id);
        return act;
    }

    @PutMapping("/DesapprouverActivite/{Activite-id}")
    public Activite dessaprover(@PathVariable("Activite-id") long id) {
        Activite act = activiteService.approuverActivite(id);
        return act;
    }

    @GetMapping("/api/pre-requis")
    public List<Pre_requis> getPreRequis() {
        return Arrays.asList(Pre_requis.values());
    }

    //@GetMapping("/plus-participants")
    //public ResponseEntity<Activite> trouverActiviteAvecPlusParticipants() {
    //    Activite activite = activiteService.trouverActiviteAvecPlusParticipants();
    //    return new ResponseEntity<>(activite, HttpStatus.OK);
    //}




    @GetMapping("/getPrixActiviteByMonth")
    public List<Float> getPrixActiviteByMonth() {
      return  activiteService.getPrixActiviteByMonth();
    }


}


