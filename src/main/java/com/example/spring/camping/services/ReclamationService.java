package com.example.spring.camping.services;

import com.example.spring.camping.models.Reclamation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReclamationService {
    Reclamation saveReclamation(Reclamation reclamation);
    Reclamation updateReclamation(Reclamation reclamation);
    void deleteReclamation(long id);
    Reclamation getReclamationById(long id);
    List<Reclamation> getAllReclamations();

    Map<String, Float> getStatisticsByReclamationType();
    Map<String, Integer> getReclamationsCountByMonth();
    List<Reclamation> getReclamationsByClientId(long idClient);
    List<Reclamation> getReclamationsByStatus(String status);
    // Check description content and classify the reclamation directly

    int getEnAttenteReclamationNumber();
    int getSolvedReclamationNumberThisMonth();
    int getSolvedReclamationNumberLastMonth();
}