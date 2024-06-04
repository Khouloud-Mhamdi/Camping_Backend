package com.example.spring.camping.respositories.CampSiteRepositories;

import com.example.spring.camping.models.CampLocations.CampSite;
import com.example.spring.camping.models.CampLocations.DetailCampSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailCampSiteRepository extends JpaRepository<DetailCampSite, Long> {

    CampSite findDetailCampSiteByCampSite(Long id);
    DetailCampSite findByDescription(String description);

    @Query("SELECT dc FROM DetailCampSite dc WHERE  dc.campSite.campsiteid = :id_campsite")
    DetailCampSite findByCampSite(@Param("id_campsite") Long id_campsite);



    //DetailCampSite findByCampSite(Long id_campsite);
}
