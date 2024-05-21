package com.example.spring.camping.respositories.ActiviteRepository;

import com.example.spring.camping.models.Activites.Activite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiviteRepository extends JpaRepository<Activite, Long> {
   // Activite findTopByOrderByParticipantsDesc();
}
