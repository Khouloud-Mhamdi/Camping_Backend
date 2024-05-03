package com.example.spring.camping.respositories;

import com.example.spring.camping.models.Reclamation;
import com.example.spring.camping.models.SuiviReclamation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuiviReclamationRepository extends JpaRepository<SuiviReclamation, Long> {
}
