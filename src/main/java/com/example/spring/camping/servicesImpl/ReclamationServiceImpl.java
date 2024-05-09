package com.example.spring.camping.servicesImpl;// ReclamationServiceImpl.java

import com.example.spring.camping.models.Reclamation;
import com.example.spring.camping.respositories.ReclamationRepository;
import com.example.spring.camping.services.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Override
    public Map<String, Float> getStatisticsByReclamationType() {
        // Implement logic to get statistics by reclamation type
        return new HashMap<>(); // Placeholder, implement your logic here
    }

    @Override
    public Map<String, Integer> getReclamationsCountByMonth() {
        // Implement logic to get reclamations count by month
        return new HashMap<>(); // Placeholder, implement your logic here
    }

    @Override
    public List<Reclamation> getReclamationsByClientId(long idClient) {
        // Implement logic to get reclamations by client id
        return reclamationRepository.findByIdClient(idClient); // Placeholder, implement your logic here
    }

    @Override
    public List<Reclamation> getReclamationsByStatus(String status) {
        // Implement logic to get reclamations by status
        return reclamationRepository.findByStatutReclamation(status); // Placeholder, implement your logic here
    }

    @Override
    public int getEnAttenteReclamationNumber() {
        return reclamationRepository.countByStatutReclamation("En attente");
    }

    @Override
    public int getSolvedReclamationNumberThisMonth() {
        LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate lastDayOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        return reclamationRepository.countByStatutReclamationAndDateResolutionBetween("Résolu", firstDayOfMonth, lastDayOfMonth);
    }

    @Override
    public int getSolvedReclamationNumberLastMonth() {
        LocalDate firstDayOfLastMonth = LocalDate.now().minusMonths(1).withDayOfMonth(1);
        LocalDate lastDayOfLastMonth = LocalDate.now().minusMonths(1).withDayOfMonth(LocalDate.now().minusMonths(1).lengthOfMonth());
        return reclamationRepository.countByStatutReclamationAndDateResolutionBetween("Résolu", firstDayOfLastMonth, lastDayOfLastMonth);
    }
}
