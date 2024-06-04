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

    @PostMapping("/add")
    public Rule addRole (@RequestBody Rule rule){
        return ruleService.add(rule);
    }


    @GetMapping("/Consulter")
    public List<Rule> ListerRules() {
        return ruleService.getAll();
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
