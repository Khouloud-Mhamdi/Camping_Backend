package com.example.spring.camping.services.ActiviteServices;

import com.example.spring.camping.models.Activites.CategorieActivite;
import com.example.spring.camping.respositories.ActiviteRepository.CategorieActiviteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategorieActiviteService implements ICRUD<CategorieActivite> {

    public CategorieActiviteRepository categorieActiviteRepository;

    @Override
    public CategorieActivite Add(CategorieActivite o) {
        CategorieActivite categorieActivite = categorieActiviteRepository.save(o);
        return categorieActivite;
    }

    @Override
    public CategorieActivite Update(CategorieActivite o) {
        CategorieActivite categorieActivite = categorieActiviteRepository.save(o);
        return categorieActivite;
    }

    @Override
    public List<CategorieActivite> RetrieveAll() {
        List<CategorieActivite> categorieActivite = categorieActiviteRepository.findAll();
        return categorieActivite;
    }

    @Override
    public void Delete(long id) {
        categorieActiviteRepository.deleteById(id);
    }

    @Override
    public Optional<CategorieActivite> RetrieveById(long id) {
        Optional<CategorieActivite> categorieActivite = categorieActiviteRepository.findById(id) ;
        return categorieActivite;
    }
}
