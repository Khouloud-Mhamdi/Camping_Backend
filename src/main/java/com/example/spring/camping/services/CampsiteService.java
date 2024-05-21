package com.example.spring.camping.services;

import com.example.spring.camping.models.CampLocations.CampSite;

import java.time.LocalDate;
import java.util.List;

public interface CampsiteService {


    List<CampSite> getAll();
    CampSite getByIdDetail (Long id);
    CampSite  add(CampSite t);
    void delete(long id);
    CampSite update(CampSite t);


    String checkMaxPlaces(Long id);
    public void AjouterCampingListeFavoris(Long idUser);
    public float calculateTotalRevenueOneCampsite(LocalDate startDate, LocalDate endDate,Long idUser);


}
