package com.example.spring.camping.services.ReclamationServices;

import com.example.spring.camping.models.Reclamation.EStatusReclamation;
import com.example.spring.camping.models.Reclamation.Reclamation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReclamationService {

    //CRUD
    Reclamation saveReclamation(Reclamation reclamation);
    Reclamation updateReclamation(Reclamation reclamation);
    void deleteReclamation(long id);
    List<Reclamation> getAllReclamations();

    //GetReclamationBy
    Reclamation getReclamationById(long id);
    List<Reclamation> getReclamationsByStatus(EStatusReclamation status);
    List<Reclamation> getReclamationsByClientId(long idClient);


    //Statistics
    Map<EStatusReclamation, Float> getStatisticsByReclamationType();
    Map<String, Integer> getReclamationsCountByMonth();
    int getPendingReclamationNumber();
    int getSolvedReclamationNumberThisMonth();
    int getSolvedReclamationNumberLastMonth();
}
