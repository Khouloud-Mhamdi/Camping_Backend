package com.example.spring.camping.respositories.CampSiteRepositories;

import com.example.spring.camping.models.CampLocations.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {

    List<Rule>findAllByCampSite(Long id_camp);

    @Query("SELECT eq FROM CampSite e,Rule eq WHERE eq.campSite.campsiteid=e.campsiteid and eq.campSite.campsiteid =:specialite" )
    List<Rule> retrtieveRulesByCampsite(@Param("specialite") Long specialite);


}
