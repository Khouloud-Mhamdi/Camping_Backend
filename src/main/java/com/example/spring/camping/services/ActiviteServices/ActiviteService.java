package com.example.spring.camping.services.ActiviteServices;

import com.example.spring.camping.models.Activites.Activite;
import com.example.spring.camping.respositories.ActiviteRepository.ActiviteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ActiviteService implements ICRUD<Activite> {
    private ActiviteRepository activiteRepository;

    @Override
    public Activite Add(Activite o) {
        Activite activite = activiteRepository.save(o);
        return activite;
    }

    @Override
    public Activite Update(Activite o) {
        Activite activite = activiteRepository.save(o);
        return activite;
    }

    @Override
    public List<Activite> RetrieveAll() {
        List<Activite> activite = activiteRepository.findAll();
        return activite;
    }

    @Override
    public void Delete(long id) {
        activiteRepository.deleteById(id);
    }

    @Override
    public Optional<Activite> RetrieveById(long id) {
        Optional<Activite> activite = activiteRepository.findById(id) ;
        return activite;
    }
}
