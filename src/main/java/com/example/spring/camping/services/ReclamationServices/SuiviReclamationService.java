package com.example.spring.camping.services.ReclamationServices;


import com.example.spring.camping.models.Reclamation.SuiviReclamation;

import java.util.List;

public interface SuiviReclamationService {
    SuiviReclamation saveSuiviReclamation(SuiviReclamation suiviReclamation);
    SuiviReclamation updateSuiviReclamation(SuiviReclamation suiviReclamation);
    void deleteSuiviReclamation(long id);
    SuiviReclamation getSuiviReclamationById(long id);
    List<SuiviReclamation> getAllSuiviReclamations();
}
