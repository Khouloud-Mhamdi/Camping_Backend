package com.example.spring.camping.controllers.CampSiteControllers;

import com.example.spring.camping.models.CampLocations.CampSite;
import com.example.spring.camping.models.CampLocations.DetailCampSite;
import com.example.spring.camping.models.CampLocations.Rule;
import com.example.spring.camping.servicesImpl.CampLocationsService.DetailCampsiteServiceImpl;
import com.example.spring.camping.servicesImpl.CampLocationsService.RuleServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/details")

@RestController
@AllArgsConstructor
public class DetailCampsiteController {


    DetailCampsiteServiceImpl detailCampsiteService;

    @PostMapping("/add/{idcamp}")
    public boolean addDetail (@RequestBody DetailCampSite detailCampSite,@PathVariable Long idcamp){
        return detailCampsiteService.add(detailCampSite,idcamp);
    }




    @GetMapping("/Rechercher/{id}")
    public DetailCampSite ConsulterDetail(@PathVariable Long id) {
        return detailCampsiteService.findbyIDCamp(id);
    }

    @PutMapping("Modifier/{id}")
    public DetailCampSite ModifierDetail(@RequestBody DetailCampSite detailCampSite) {
        return detailCampsiteService.update(detailCampSite);
    }

    @PutMapping("Supprimer/{id}")
    public void SupprimerDetail(@PathVariable Long id) {
        detailCampsiteService.delete(id);
    }


    @GetMapping("/RechercherByCampsite/{id_camp}")
    public DetailCampSite findByCampSite(@PathVariable Long id_camp) {
        return detailCampsiteService.findByCampSite(id_camp);
    }


}
