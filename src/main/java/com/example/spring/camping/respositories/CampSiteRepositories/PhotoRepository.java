package com.example.spring.camping.respositories.CampSiteRepositories;

import com.example.spring.camping.models.CampLocations.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
