// ReclamationRepository.java

package com.example.spring.camping.respositories.ReclamationRepository;

import com.example.spring.camping.models.Reclamation.EStatusReclamation;
import com.example.spring.camping.models.Reclamation.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {
    List<Reclamation> findByIdClient(long idClient); // Change method name to match the property
    List<Reclamation> findByStatutReclamation(EStatusReclamation statut);

    int countByStatutReclamation(String status);

    int countByStatutReclamationAndDateResolutionBetween(String status, LocalDate startDate, LocalDate endDate);

    @Query("SELECT r.statutReclamation, COUNT(r) FROM Reclamation r GROUP BY r.statutReclamation")
    List<Object[]> countReclamationsByType();

    @Query("SELECT r.statutReclamation, COUNT(r) FROM Reclamation r GROUP BY r.statutReclamation")
    List<Object[]> getStatisticsByReclamationType();


    int countByDateReclamationBetween(Date startDate, Date endDate);


}
