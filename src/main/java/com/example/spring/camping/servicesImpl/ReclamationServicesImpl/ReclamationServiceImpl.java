package com.example.spring.camping.servicesImpl.ReclamationServicesImpl;// ReclamationServiceImpl.java

import com.example.spring.camping.models.Reclamation.EStatusReclamation;
import com.example.spring.camping.models.Reclamation.Reclamation;
import com.example.spring.camping.respositories.ReclamationRepository.ReclamationRepository;
import com.example.spring.camping.services.ReclamationServices.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class ReclamationServiceImpl implements ReclamationService {
    @Autowired
    private ReclamationRepository reclamationRepository;

    //CRUD
    @Override
    public List<Reclamation> getAllReclamations() {
        return reclamationRepository.findAll();
    }

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


    //GetReclamationBy
    @Override
    public Reclamation getReclamationById(long id) {
        Optional<Reclamation> optionalReclamation = reclamationRepository.findById(id);
        return optionalReclamation.orElse(null);
    }

    @Override
    public List<Reclamation> getReclamationsByClientId(long idClient) {
        return reclamationRepository.findByIdClient(idClient);
    }

    @Override
    public List<Reclamation> getReclamationsByStatus(EStatusReclamation status) {
        return reclamationRepository.findByStatutReclamation(status);
    }

    //Statistics
    public Map<EStatusReclamation, Float> getStatisticsByReclamationType() {
        List<Object[]> result = reclamationRepository.getStatisticsByReclamationType();
        Map<EStatusReclamation, Float> statistics = new EnumMap<>(EStatusReclamation.class);

        for (Object[] objects : result) {
            EStatusReclamation status = (EStatusReclamation) objects[0];
            Long count = (Long) objects[1];
            statistics.put(status, count.floatValue());
        }

        return statistics;
    }

    @Override
    public Map<String, Integer> getReclamationsCountByMonth() {
        return new HashMap<>();
    }


    @Override
    public int getPendingReclamationNumber() {
        return reclamationRepository.countByStatutReclamation("Pending");
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


    @Override
    public int getReclamationNumberCreatedToday() {
        LocalDateTime startOfToday = LocalDate.now().atStartOfDay();
        LocalDateTime endOfToday = LocalDateTime.now();

        Date startDate = Date.from(startOfToday.atZone(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endOfToday.atZone(ZoneId.systemDefault()).toInstant());

        return reclamationRepository.countByDateReclamationBetween(startDate, endDate);
    }
}
