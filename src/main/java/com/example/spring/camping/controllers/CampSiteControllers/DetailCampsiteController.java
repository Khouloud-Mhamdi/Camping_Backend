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

    @PostMapping("/add")
    public DetailCampSite addDetail (@RequestBody DetailCampSite detailCampSite){
        return detailCampsiteService.add(detailCampSite);
    }




    @GetMapping("Rechercher/{id}")
    public DetailCampSite ConsulterDetail(@PathVariable Long id) {
        return detailCampsiteService.findbyIDCamp(id);
    }

    @PutMapping("Modifier/{id}")
    public DetailCampSite ModifierDetail(@RequestBody DetailCampSite detailCampSite) {
        return detailCampsiteService.update(detailCampSite);
    }

    @DeleteMapping("Supprimer/{id}")
    public void SupprimerDetail(@PathVariable Long id) {
        detailCampsiteService.delete(id);
    }


}
