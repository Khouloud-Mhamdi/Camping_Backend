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

    @PostMapping("/add/{lieu}")
    public boolean addDetail (@RequestBody DetailCampSite detailCampSite,@PathVariable String lieu){
        return detailCampsiteService.add(detailCampSite,lieu);
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


}
