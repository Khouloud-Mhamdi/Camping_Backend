package com.example.spring.camping.servicesImpl.CampLocationsService;

import com.example.spring.camping.models.CampLocations.CampSite;
import com.example.spring.camping.models.CampLocations.DetailCampSite;
import com.example.spring.camping.respositories.CampSiteRepositories.CampsiteRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.DetailCampSiteRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.PhotoRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.RuleRepository;
import com.example.spring.camping.services.CampsitesService.DetailCampsiteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Slf4j
@Service
public class DetailCampsiteServiceImpl implements DetailCampsiteService {


    RuleRepository ruleRepository;
    CampsiteRepository campsiteRepo;
    DetailCampSiteRepository detailCampSiteRepository;
    PhotoRepository photoRepository;



@Override
    public DetailCampSite findbyIDCamp(Long id) {
        return detailCampSiteRepository.findById(id).orElse(null);
    }

    @Override
    public boolean add(DetailCampSite detailCampSite,Long idcamp) {
        CampSite campSite=campsiteRepo.findById(idcamp).get();
        System.out.println(campSite);
        detailCampSite.setCampSite(campSite);
        campSite.setDetailCampSite(detailCampSite);
        detailCampSiteRepository.save(detailCampSite);
        return true;
    }
@Override
    public void delete(long id) {
    detailCampSiteRepository.deleteById(id);
    }

    @Override
    public DetailCampSite update(DetailCampSite detailCampSite) {
        return detailCampSiteRepository.save(detailCampSite);
    }


    @Override
    public DetailCampSite findByCampSite(Long id_campsite) {
        return detailCampSiteRepository.findByCampSite(id_campsite);
    }
}
