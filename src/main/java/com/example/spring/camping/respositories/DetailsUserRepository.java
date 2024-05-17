package com.example.spring.camping.respositories;

import com.example.spring.camping.models.ManageUsers.DetailsUser;
import com.example.spring.camping.models.ManageUsers.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetailsUserRepository extends JpaRepository<DetailsUser, Long> {

    @Query("SELECT d.saison, COUNT(d) FROM DetailsUser " +
            " d GROUP BY d.saison ORDER BY COUNT(d) DESC")
    List<Object[]> findFavoriteSeasonStatistics();

    @Query("SELECT d.paysage, COUNT(d) FROM DetailsUser d GROUP BY d.paysage ORDER BY COUNT(d) DESC")
    List<Object[]> findFavoriteLandscapeStatistics();

    @Query("SELECT d.type_hebergement, COUNT(d) FROM DetailsUser d GROUP BY d.type_hebergement ORDER BY COUNT(d) DESC")
    List<Object[]> findFavoriteAccommodationTypeStatistics();


}
