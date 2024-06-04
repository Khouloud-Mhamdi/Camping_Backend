package com.example.spring.camping.services.CampsitesService;

import com.example.spring.camping.models.CampLocations.DetailCampSite;

public interface DetailCampsiteService {

    public boolean add(DetailCampSite detailCampSite,Long idcamp );

    public DetailCampSite findbyIDCamp(Long id);

    public void delete(long id);

    public DetailCampSite update(DetailCampSite detailCampSite);
    DetailCampSite findByCampSite(Long id_campsite);

}
