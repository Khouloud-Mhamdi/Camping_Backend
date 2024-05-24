package com.example.spring.camping.respositories.CampSiteRepositories;

import com.example.spring.camping.models.CampLocations.CampSite;
import com.example.spring.camping.models.CampLocations.DetailCampSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampsiteRepository extends JpaRepository<CampSite,Long> {

    CampSite findByDetailCampSite(Long id);
    @Query("SELECT c FROM CampSite c WHERE  c.detailCampSite.detailcampid = :id")
    CampSite findByDetail(@Param("id") Long id);
    CampSite findByLieu(String lieu);

}
