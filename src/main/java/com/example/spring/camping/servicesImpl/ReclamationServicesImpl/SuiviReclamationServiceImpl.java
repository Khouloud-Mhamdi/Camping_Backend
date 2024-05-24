package com.example.spring.camping.servicesImpl.ReclamationServicesImpl;
import com.example.spring.camping.models.Reclamation.SuiviReclamation;

import com.example.spring.camping.respositories.ReclamationRepository.SuiviReclamationRepository;
import com.example.spring.camping.services.ReclamationServices.SuiviReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuiviReclamationServiceImpl implements SuiviReclamationService {

    @Autowired
    private SuiviReclamationRepository suiviReclamationRepository;

    @Override
    public SuiviReclamation saveSuiviReclamation(SuiviReclamation suiviReclamation) {
        return suiviReclamationRepository.save(suiviReclamation);
    }

    @Override
    public SuiviReclamation updateSuiviReclamation(SuiviReclamation suiviReclamation) {
        return suiviReclamationRepository.save(suiviReclamation);
    }

    @Override
    public void deleteSuiviReclamation(long id) {
        suiviReclamationRepository.deleteById(id);
    }

    @Override
    public SuiviReclamation getSuiviReclamationById(long id) {
        Optional<SuiviReclamation> optionalSuiviReclamation = suiviReclamationRepository.findById(id);
        return optionalSuiviReclamation.orElse(null);
    }

    @Override
    public List<SuiviReclamation> getAllSuiviReclamations() {
        return suiviReclamationRepository.findAll();
    }
}
