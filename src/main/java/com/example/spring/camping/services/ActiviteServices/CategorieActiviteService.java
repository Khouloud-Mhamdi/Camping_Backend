package com.example.spring.camping.services.ActiviteServices;

import com.example.spring.camping.models.Activites.CategorieActivite;

public interface CategorieActiviteService<CategorieActivite> {

    CategorieActivite AjouterCategorieActivite(CategorieActivite ca);
    void DeleteCategorieActivite(CategorieActivite ca);
    CategorieActivite UpdateCategorieActivite(CategorieActivite ca);
}
