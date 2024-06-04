package com.example.spring.camping.respositories.ActiviteRepository;

import com.example.spring.camping.models.Activites.Activite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActiviteRepository extends JpaRepository<Activite, Long> {
   // Activite findTopByOrderByParticipantsDesc();


        @Query("SELECT SUM(a.prix) FROM Activite a WHERE MONTH(a.Date) = :month")
        Float getPrixActiviteByMonth(@Param("month") Integer month);
}
