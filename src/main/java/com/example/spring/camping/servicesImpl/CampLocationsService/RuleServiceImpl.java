package com.example.spring.camping.servicesImpl.CampLocationsService;

import com.example.spring.camping.models.CampLocations.CampSite;
import com.example.spring.camping.models.CampLocations.Rule;
import com.example.spring.camping.respositories.CampSiteRepositories.CampsiteRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.DetailCampSiteRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.PhotoRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.RuleRepository;
import com.example.spring.camping.services.CampsitesService.RuleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class RuleServiceImpl implements RuleService {
    RuleRepository ruleRepository;
    CampsiteRepository campsiteRepo;
    DetailCampSiteRepository detailCampSiteRepository;
    PhotoRepository photoRepository;
    @Override
    public List<Rule> getAll() {
        return ruleRepository.findAll();
    }

    @Override
    public Rule getById(long id) {
        return ruleRepository.findById(id).orElse(null);
    }

    //add+affecter
    @Override
    public Rule add(Rule o,Long id_camp) {
        CampSite campSite=campsiteRepo.findById(id_camp).get();
        o.setCampSite(campSite);
        Set<Rule>rules=new HashSet<>();
        rules.add(o);
        campSite.setRules(rules);
        return ruleRepository.save(o);
    }

    @Override
    public void delete(long id) {
        ruleRepository.deleteById(id);
    }

    @Override
    public Rule update(Rule o) {
        return ruleRepository.save(o);
    }

    //retrieving all rules by idcamp
    @Override
    public List<Rule> findAllByCampSite(Long id_camp) {
        CampSite campSite=campsiteRepo.findById(id_camp).get();
        List<Rule> rules =new ArrayList<>();
        rules.addAll(campSite.getRules());
        return rules;
    }

    /*
    @Transactional
    public Rule addRuleToCampsite(Rule rule, Long campsite_id) {

        CampSite campSite = campsiteRepo.findById(campsite_id).orElseThrow(() -> new RuntimeException("campsite not found"));
        Set<CampSite>campSites=new HashSet<>();
if(rule.getCampSites()==null){
    campSites.add(campSite);
    rule.setCampSites(campSites);
}else{
    rule.getCampSites().add(campSite);
}
        campSite.getRules().add(rule);
        ruleRepository.save(rule);
        return rule;
    }*/
}
