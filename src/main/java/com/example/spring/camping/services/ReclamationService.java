package com.example.spring.camping.services;

import com.example.spring.camping.models.Reclamation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReclamationService {
    Reclamation saveReclamation(Reclamation reclamation);
    Reclamation updateReclamation(Reclamation reclamation);
    void deleteReclamation(long id);
    Reclamation getReclamationById(long id);
    List<Reclamation> getAllReclamations();
}
