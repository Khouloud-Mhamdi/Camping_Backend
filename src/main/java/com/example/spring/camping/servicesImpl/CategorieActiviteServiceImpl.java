package com.example.spring.camping.servicesImpl;

import com.example.spring.camping.models.Activites.CategorieActivite;
import com.example.spring.camping.respositories.ActiviteRepository.CategorieActiviteRepository;
import com.example.spring.camping.services.ActiviteServices.CategorieActiviteService;
import com.example.spring.camping.services.ActiviteServices.ICRUD;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategorieActiviteServiceImpl implements CategorieActiviteService<CategorieActivite> {

    public CategorieActiviteRepository categorieActiviteRepository;


    @Override
    public CategorieActivite AjouterCategorieActivite(CategorieActivite ca) {
        CategorieActivite Categoriee = categorieActiviteRepository.save(ca);
        return Categoriee;
    }

    @Override
    public void DeleteCategorieActivite(CategorieActivite ca) {
        categorieActiviteRepository.delete(ca);
    }

    @Override
    public CategorieActivite UpdateCategorieActivite(CategorieActivite ca) {
        CategorieActivite Categoriee = categorieActiviteRepository.save(ca);
        return Categoriee;
    }
}
