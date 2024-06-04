package com.example.spring.camping.controllers.CampSiteControllers;

import com.example.spring.camping.models.CampLocations.Rule;
import com.example.spring.camping.models.ManageUsers.Role;
import com.example.spring.camping.servicesImpl.CampLocationsService.RuleServiceImpl;
import com.example.spring.camping.servicesImpl.RoleServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rules")
@AllArgsConstructor
public class RuleController {

    RuleServiceImpl ruleService;

    @PostMapping("/add/{id_camp}")
    public Rule addRule (@RequestBody Rule rule,@PathVariable Long id_camp){
        return ruleService.add(rule,id_camp);
    }
 /*
    //ajout+ affectation rule to campsite
    @PostMapping("/add/{campsite_id}")
    public Rule addRole (@RequestBody Rule rule,@PathVariable Long campsite_id){
        return ruleService.addRuleToCampsite(rule,campsite_id);
    }
*/

    @GetMapping("/Consulter")
    public List<Rule> ListerRules() {
        return ruleService.getAll();
    }

    @GetMapping("/consulter/{id_camp}")
    public List<Rule> ListerRulesByCampsites(@PathVariable Long id_camp) {
        return ruleService.findAllByCampSite(id_camp);
    }
    @GetMapping("Rechercher/{id}")
    public Rule ConsulterURule(@PathVariable Long id) {
        return ruleService.getById(id);
    }

    @PutMapping("Modifier/{id}")
    public Rule ModifierRule(@RequestBody Rule rule) {
        return ruleService.update(rule);
    }

    @DeleteMapping("Supprimer/{id}")
    public void SupprimerRule(@PathVariable Long id) {
        ruleService.delete(id);
    }
}
