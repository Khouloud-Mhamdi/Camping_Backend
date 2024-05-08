package com.example.spring.camping.controllers.ActiviteController;

import com.example.spring.camping.models.Activites.Activite;

import com.example.spring.camping.servicesImpl.ActiviteServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public Optional<Activite> RetrieveById(@PathVariable("Bloc-id") long id) {
        Optional<Activite> Activite = activiteService.retrieveActiviteById(id);
        return Activite;
    }

    @PostMapping("/AjoutActivite")
    public Activite Add(@RequestBody Activite a){
       Activite activite = activiteService.ajoutActivite(a);
        return activite;
    }

    @PutMapping("/UpdateActivite")
    public Activite Update(@RequestBody Activite a){
        Activite activite = activiteService.updateActivite(a);
        return activite;
    }

    @DeleteMapping("/DeleteActivite/{Activite-id}")
    public void Delete(@PathVariable("Activite-id") long id) {
        activiteService.delete(id);
    }
}

