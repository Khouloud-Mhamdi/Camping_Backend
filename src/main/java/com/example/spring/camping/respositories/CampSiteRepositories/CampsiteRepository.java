package com.example.spring.camping.respositories.CampSiteRepositories;

import com.example.spring.camping.models.CampLocations.CampSite;
import com.example.spring.camping.models.CampLocations.DetailCampSite;
import com.example.spring.camping.models.CampLocations.Rule;
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

    //retrieve all campsites by user id
    @Query("SELECT c FROM CampSite c WHERE  c.id_user = :id_user")
    List<CampSite>  findcampsitesbyuser(@Param("id_user") Long id_user);

    @Query("SELECT c FROM CampSite c WHERE  c.status = 'pending' ")
    List<CampSite>  findpendingcampsitesforadmin();

    //retrieve all rules
    List<Rule>findAllByDetailCampSite_Detailcampid(Long id_detail);

    @Query("SELECT e.campsiteid FROM CampSite e WHERE e.detailCampSite.detailcampid =:detailcampid" )
    Long retrieveEquipeBySpecialite(@Param("detailcampid") Long detailcampid);


}
