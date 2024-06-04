package com.example.spring.camping.services.CampsitesService;

import com.example.spring.camping.models.CampLocations.CampSite;
import com.example.spring.camping.models.CampLocations.DetailCampSite;
import com.example.spring.camping.models.CampLocations.Rule;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CampsiteService {


    List<CampSite> getAll();
    CampSite getByIdDetail (Long id);
    CampSite  add(CampSite t,long id_user);
    void delete(long id);
    CampSite update(CampSite t);



    //sénario admin approuver
    CampSite updateCampsiteforAdminApprove(Long id_camp);

    //sénario admin desapprouver
    CampSite updateCampsiteforAdminDis(Long id_camp);

    String checkMaxPlaces(Long id);
    public void AjouterCampingListeFavoris(Long idUser);
    public float calculateTotalRevenueOneCampsite(LocalDate startDate, LocalDate endDate,Long idUser);


    List<CampSite>  findcampsitesbyuser(Long id_user);
    List<CampSite>  findpendingcampsitesforadmin();
    //fetch rules by detail

    List<Rule>findAllByDetailCampSite(Long id_detail);



}
