package com.example.spring.camping.services;


import com.example.spring.camping.models.SuiviReclamation;

import java.util.List;

public interface SuiviReclamationService {
    SuiviReclamation saveSuiviReclamation(SuiviReclamation suiviReclamation);
    SuiviReclamation updateSuiviReclamation(SuiviReclamation suiviReclamation);
    void deleteSuiviReclamation(long id);
    SuiviReclamation getSuiviReclamationById(long id);
    List<SuiviReclamation> getAllSuiviReclamations();
}