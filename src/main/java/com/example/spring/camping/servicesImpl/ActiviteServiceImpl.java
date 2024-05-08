package com.example.spring.camping.servicesImpl;

import com.example.spring.camping.models.Activites.Activite;
import com.example.spring.camping.respositories.ActiviteRepository.ActiviteRepository;
import com.example.spring.camping.services.ActiviteServices.ActiviteService;
import com.example.spring.camping.services.ActiviteServices.ICRUD;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ActiviteServiceImpl implements ActiviteService<Activite> {

    private ActiviteRepository activiteRepository;

    @Override
    public Activite ajoutActivite(Activite o) {
        Activite activite = activiteRepository.save(o);
        return activite;
    }

    @Override
    public Activite updateActivite(Activite o) {
        Activite activite = activiteRepository.save(o);
        return activite;
    }

    @Override
    public List<Activite> retrieveAll() {
        List<Activite> activite = activiteRepository.findAll();
        return activite;
    }

    @Override
    public void delete(long id) {
        activiteRepository.deleteById(id);
    }

    @Override
    public Optional<Activite> retrieveActiviteById(long id) {
        Optional<Activite> activite = activiteRepository.findById(id) ;
        return activite;
    }

    @Override
    public Activite approuverActivite(long ActiviteID) {
        Activite activite = activiteRepository.findById(ActiviteID).get();
          activite.setStatus(true);
        return activite;
    }

    @Override
    public Activite desapprouverActivite(long ActiviteID) {
        Activite activite = activiteRepository.findById(ActiviteID).get();
        activite.setStatus(false);
        return activite;
    }

    @Override
    public Activite participerActivite(Activite a) {
        return null;
    }

    @Override
    public List<Activite> rechercheActivites(String critere) {
        return null;
    }

    @Override
    public Activite annulerActivite(Activite a) {
        return null;
    }

}
