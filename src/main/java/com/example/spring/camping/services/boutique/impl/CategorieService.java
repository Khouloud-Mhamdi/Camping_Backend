package com.example.spring.camping.services.boutique.impl;


import com.example.spring.camping.models.boutique.CategoriesProduct;
import com.example.spring.camping.respositories.boutique.CategoriesProductRepository;
import com.example.spring.camping.services.boutique.ICategorieProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategorieService implements ICategorieProductService {
    CategoriesProductRepository categoriesProductRepository;
    @Override
    public List<CategoriesProduct> retrieveAllCategoriesProducts() {
        return categoriesProductRepository.findAll();
    }

    @Override
    public CategoriesProduct addCategoriesProduct(CategoriesProduct f) {
        return categoriesProductRepository.save(f);
    }

    @Override
    public CategoriesProduct updateCategoriesProduct(CategoriesProduct f) {
        return categoriesProductRepository.save(f);
    }

    @Override
    public CategoriesProduct retrieveCategoriesProduct(Long idCategoriesProduct) {
        return categoriesProductRepository.findById(idCategoriesProduct).orElse(null);
    }
}
