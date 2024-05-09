// ReclamationRepository.java

package com.example.spring.camping.respositories;

import com.example.spring.camping.models.ManageUsers.Role;
import com.example.spring.camping.models.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {
    List<Reclamation> findByIdClient(long idClient); // Change method name to match the property
    List<Reclamation> findByStatutReclamation(String status);

    int countByStatutReclamation(String status);

    int countByStatutReclamationAndDateResolutionBetween(String status, LocalDate startDate, LocalDate endDate);
}
