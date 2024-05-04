package com.example.spring.camping.servicesImpl.CampLocationsService;

import com.example.spring.camping.models.CampLocations.CampSite;
import com.example.spring.camping.respositories.CampSiteRepositories.CampsiteRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.DetailCampSiteRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.PhotoRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.RuleRepository;
import com.example.spring.camping.services.ICrud;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Slf4j
@Service
public class CampsiteServiceImpl implements ICrud<CampSite> {

    RuleRepository ruleRepository;
    CampsiteRepository campsiteRepo;
    DetailCampSiteRepository detailCampSiteRepository;
    PhotoRepository photoRepository;

    @Override
    public List<CampSite> getAll() {
        return campsiteRepo.findAll();
    }

    @Override
    public CampSite getById(long id) {
        return campsiteRepo.findById(id).orElse(null);
    }

    @Override
    public CampSite add(CampSite o) {
        return campsiteRepo.save(o);
    }

    @Override
    public void delete(long id) {
        campsiteRepo.deleteById(id);
    }

    @Override
    public CampSite update(CampSite o) {
        return campsiteRepo.save(o);
    }
}
