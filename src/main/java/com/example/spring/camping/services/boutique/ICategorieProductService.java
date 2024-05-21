package com.example.spring.camping.services.boutique;


import com.example.spring.camping.models.boutique.CategoriesProduct;

import java.util.List;

public interface ICategorieProductService {
    List<CategoriesProduct> retrieveAllCategoriesProducts();
    CategoriesProduct addCategoriesProduct(CategoriesProduct f);
    CategoriesProduct updateCategoriesProduct(CategoriesProduct f);
    CategoriesProduct retrieveCategoriesProduct(Long idCategoriesProduct);
}
