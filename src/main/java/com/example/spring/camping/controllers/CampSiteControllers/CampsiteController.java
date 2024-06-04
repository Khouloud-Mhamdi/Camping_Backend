package com.example.spring.camping.controllers.CampSiteControllers;

import com.example.spring.camping.models.CampLocations.CampSite;
import com.example.spring.camping.models.CampLocations.Rule;
import com.example.spring.camping.respositories.CampSiteRepositories.CampsiteRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.RuleRepository;
import com.example.spring.camping.servicesImpl.CampLocationsService.CampsiteServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/campsites")
@AllArgsConstructor
public class CampsiteController {

   CampsiteServiceImpl campsiteService;
RuleRepository ruleRepository;
    @PostMapping("/add/{id_user}")
    public CampSite addCampsite (@RequestBody CampSite campSite,@PathVariable long id_user){
        return campsiteService.add(campSite,id_user);
    }


    @GetMapping("/Consulter")
    public List<CampSite> ListerCampsite() {
        return campsiteService.getAll();

    }

    @GetMapping("/Rechercher/{id}")
    public CampSite ConsulterCampsite(@PathVariable Long id) {
        return campsiteService.getByIdDetail(id);
    }

    @PutMapping("Modifier/{id}")
    public CampSite ModifierCampsite(@RequestBody CampSite  campSite) {
        return campsiteService.update(campSite);
    }
    @PutMapping("ModifierCampsiteParAdmin/{idCamp}")
    public CampSite ModifierCampsiteParAdmin(@PathVariable Long idCamp) {
        return campsiteService.updateCampsiteforAdminApprove(idCamp);
    }

    @PutMapping("ModifierCampsiteParAdminDis/{idCamp}")
    public CampSite ModifierCampsiteParAdminDis(@PathVariable Long idCamp) {
        return campsiteService.updateCampsiteforAdminDis(idCamp);
    }

    @PutMapping("Supprimer/{id}")
    public void SupprimerCampsite(@PathVariable Long id) {
        campsiteService.delete(id);
    }

//lister les campsite par utilisateur (centre de camping)
    @GetMapping("/findcampsitesbyuser/{id_user}")
    public List<CampSite> findcampsitesbyuser(@PathVariable long id_user) {
        return campsiteService.findcampsitesbyuser(id_user);
    }

    @GetMapping("/findpendingcampsitesforadmin")
    public List<CampSite> findpendingcampsitesforadmin() {
        return campsiteService.findpendingcampsitesforadmin();
    }

    //retrieve all rules
    @GetMapping("/findAllRuleByCampSite/{id_camp}")
    public List<Rule> findAllByCampSite(@PathVariable Long id_camp) {
        return ruleRepository.retrtieveRulesByCampsite(id_camp);
    }

}
