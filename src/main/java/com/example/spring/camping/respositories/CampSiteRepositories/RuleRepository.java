package com.example.spring.camping.respositories.CampSiteRepositories;

import com.example.spring.camping.models.CampLocations.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {
}
