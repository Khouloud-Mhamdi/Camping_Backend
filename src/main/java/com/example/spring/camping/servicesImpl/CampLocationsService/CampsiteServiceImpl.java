package com.example.spring.camping.servicesImpl.CampLocationsService;

import com.example.spring.camping.models.CampLocations.CampSite;
import com.example.spring.camping.models.CampLocations.Rule;
import com.example.spring.camping.models.CampLocations.Status;
import com.example.spring.camping.respositories.CampSiteRepositories.CampsiteRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.DetailCampSiteRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.PhotoRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.RuleRepository;
import com.example.spring.camping.services.CampsitesService.CampsiteService;
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
    public CampSite add(CampSite o,long id_user) {
        o.setArchived(false);
        o.setStatus(Status.pending);
        o.setId_user(id_user);
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

    //sénario admin approuver
    @Override
    public CampSite updateCampsiteforAdminApprove(Long id_camp) {
        CampSite campSite=campsiteRepo.findById(id_camp).get();
        campSite.setStatus(Status.approuved);
        campsiteRepo.save(campSite);
        return campSite ;
    }


    //sénario admin desapprouver
    @Override
    public CampSite updateCampsiteforAdminDis(Long id_camp) {

        CampSite campSite=campsiteRepo.findById(id_camp).get();
        campSite.setStatus(Status.disapproved);
        campsiteRepo.save(campSite);
        return campSite ;
    }
    //pour l'affichage lel user
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
//implementation for retrieving all campsites by user id
    @Override
    public List<CampSite> findcampsitesbyuser(Long id_user) {

        return campsiteRepo.findcampsitesbyuser(id_user);
    }

    @Override
    public List<CampSite> findpendingcampsitesforadmin() {
        return campsiteRepo.findpendingcampsitesforadmin();
    }

    @Override
    public List<Rule> findAllByDetailCampSite(Long id_detail) {
        return campsiteRepo.findAllByDetailCampSite_Detailcampid(id_detail);
    }


}
