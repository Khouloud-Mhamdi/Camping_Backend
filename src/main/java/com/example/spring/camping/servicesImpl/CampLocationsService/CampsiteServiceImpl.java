package com.example.spring.camping.servicesImpl.CampLocationsService;

import com.example.spring.camping.models.CampLocations.CampSite;
import com.example.spring.camping.respositories.CampSiteRepositories.CampsiteRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.DetailCampSiteRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.PhotoRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.RuleRepository;
import com.example.spring.camping.services.CampsiteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@Slf4j
@Service
public class CampsiteServiceImpl implements CampsiteService {

    RuleRepository ruleRepository;
    CampsiteRepository campsiteRepo;
    DetailCampSiteRepository detailCampSiteRepository;
    PhotoRepository photoRepository;

    @Override
    public List<CampSite> getAll() {
        return campsiteRepo.findAll();
    }

    @Override
    public CampSite getByIdDetail(Long id) {
        CampSite campSite=campsiteRepo.findByDetail(id);
        return campSite;
    }



    @Override
    public CampSite add(CampSite o) {
        o.setArchived(false);
        o.setStatus(false);
        return campsiteRepo.save(o);
    }

    @Override
    public void delete(long id) {
        CampSite campSite=campsiteRepo.findByDetailCampSite(id);
        campSite.setArchived(true);
        campsiteRepo.save(campSite);
    }

    @Override
    public CampSite update(CampSite o) {
        return campsiteRepo.save(o);
    }

    @Override
    public String checkMaxPlaces(Long id) {
        CampSite campSite=campsiteRepo.findById(id).get();
        if(campSite.getPlaces()==0)
            return "No more places available";
       else{
           return "only "+campSite.getPlaces()+" are left";
        }
    }


    //Ajouter un campsite a une liste favoris
    @Override
    public void AjouterCampingListeFavoris(Long idUser) {

    }

    //calculer le revenue entre 2 dates (stat)  besoin du module reservation
   @Override
    public float calculateTotalRevenueOneCampsite(LocalDate startDate, LocalDate endDate,Long idUser) {

        return 0.0f;
    }

}
