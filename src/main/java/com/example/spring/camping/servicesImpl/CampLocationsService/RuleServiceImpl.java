package com.example.spring.camping.servicesImpl.CampLocationsService;

import com.example.spring.camping.models.CampLocations.Rule;
import com.example.spring.camping.respositories.CampSiteRepositories.CampsiteRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.DetailCampSiteRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.PhotoRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.RuleRepository;
import com.example.spring.camping.services.ICrud;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@AllArgsConstructor
@Service
public class RuleServiceImpl implements ICrud<Rule> {
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

    @Override
    public Rule add(Rule o) {
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
}
