package com.example.spring.camping.servicesImpl;
import com.example.spring.camping.models.Reclamation;

import com.example.spring.camping.respositories.ReclamationRepository;
import com.example.spring.camping.services.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReclamationServiceImpl implements ReclamationService {

    @Autowired
    private ReclamationRepository reclamationRepository;

    @Override
    public Reclamation saveReclamation(Reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }

    @Override
    public Reclamation updateReclamation(Reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }

    @Override
    public void deleteReclamation(long id) {
        reclamationRepository.deleteById(id);
    }

    @Override
    public Reclamation getReclamationById(long id) {
        Optional<Reclamation> optionalReclamation = reclamationRepository.findById(id);
        return optionalReclamation.orElse(null);
    }

    @Override
    public List<Reclamation> getAllReclamations() {
        return reclamationRepository.findAll();
    }
}