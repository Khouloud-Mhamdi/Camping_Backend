package com.example.spring.camping.respositories.CampSiteRepositories;

import com.example.spring.camping.models.CampLocations.CampSite;
import com.example.spring.camping.models.CampLocations.DetailCampSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailCampSiteRepository extends JpaRepository<DetailCampSite, Long> {

    CampSite findDetailCampSiteByCampSite(Long id);
}
