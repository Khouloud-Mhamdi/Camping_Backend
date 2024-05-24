package com.example.spring.camping.respositories.ActiviteRepository;

import com.example.spring.camping.models.Activites.CategorieActivite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieActiviteRepository extends JpaRepository<CategorieActivite, Long> {
}
