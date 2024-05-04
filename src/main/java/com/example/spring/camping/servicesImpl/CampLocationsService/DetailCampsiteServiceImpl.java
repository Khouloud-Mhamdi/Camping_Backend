package com.example.spring.camping.servicesImpl.CampLocationsService;

import com.example.spring.camping.models.CampLocations.CampSite;
import com.example.spring.camping.models.CampLocations.DetailCampSite;
import com.example.spring.camping.respositories.CampSiteRepositories.CampsiteRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.DetailCampSiteRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.PhotoRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.RuleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Slf4j
@Service

public class DetailCampsiteServiceImpl  {


    RuleRepository ruleRepository;
    CampsiteRepository campsiteRepo;
    DetailCampSiteRepository detailCampSiteRepository;
    PhotoRepository photoRepository;




    public DetailCampSite findbyIDCamp(Long id) {
        return detailCampSiteRepository.findById(id).orElse(null);
    }

    public DetailCampSite add(DetailCampSite detailCampSite) {
        return detailCampSiteRepository.save(detailCampSite);
    }

    public void delete(long id) {
    detailCampSiteRepository.deleteById(id);
    }

    public DetailCampSite update(DetailCampSite detailCampSite) {
        return detailCampSiteRepository.save(detailCampSite);
    }


}
