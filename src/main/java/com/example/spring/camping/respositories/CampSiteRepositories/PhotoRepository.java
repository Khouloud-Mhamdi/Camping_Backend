package com.example.spring.camping.respositories.CampSiteRepositories;

import com.example.spring.camping.models.CampLocations.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Query("SELECT p.image FROM Photo p WHERE  p.detailCampSites.detailcampid = :id")
    List<byte[]> findAllByDetailCampSiteId(@Param("id") Long id);

    @Query("SELECT p.imageUrl FROM Photo p WHERE  p.detailCampSites.detailcampid = :id")
    List<String> findAllByDetailCampSiteId1(@Param("id") Long id);


}
