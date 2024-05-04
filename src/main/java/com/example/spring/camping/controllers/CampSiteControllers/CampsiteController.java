package com.example.spring.camping.controllers.CampSiteControllers;

import com.example.spring.camping.models.CampLocations.CampSite;
import com.example.spring.camping.servicesImpl.CampLocationsService.CampsiteServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/campsites")
@AllArgsConstructor
public class CampsiteController {


   CampsiteServiceImpl campsiteService;


    @PostMapping("/add")
    public CampSite addCampsite (@RequestBody CampSite campSite){
        return campsiteService.add(campSite);
    }


    @GetMapping("/Consulter")
    public List<CampSite> ListerCampsite() {
        return campsiteService.getAll();
    }

    @GetMapping("Rechercher/{id}")
    public CampSite ConsulterCampsite(@PathVariable Long id) {
        return campsiteService.getById(id);
    }

    @PutMapping("Modifier/{id}")
    public CampSite ModifierCampsite(@RequestBody CampSite  campSite) {
        return campsiteService.update(campSite);
    }

    @DeleteMapping("Supprimer/{id}")
    public void SupprimerCampsite(@PathVariable Long id) {
        campsiteService.delete(id);
    }
}
