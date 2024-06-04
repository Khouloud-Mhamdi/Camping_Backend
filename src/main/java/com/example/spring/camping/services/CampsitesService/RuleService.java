package com.example.spring.camping.services.CampsitesService;

import com.example.spring.camping.models.CampLocations.Rule;

import java.util.List;

public interface RuleService {
    List<Rule> getAll();

    Rule getById(long id);

    Rule add(Rule o,Long id_camp);

    void delete(long id);

    Rule update(Rule o);

    List<Rule>findAllByCampSite(Long id_camp);

}
