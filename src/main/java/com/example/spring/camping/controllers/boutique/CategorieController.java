package com.example.spring.camping.controllers.boutique;


import com.example.spring.camping.models.boutique.CategoriesProduct;
import com.example.spring.camping.services.boutique.ICategorieProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/categorieProduct")

public class CategorieController {

    ICategorieProductService categorieProductService;

    @GetMapping("/retrieve-all-categorieProducts")
    public List<CategoriesProduct> getCategoriesProducts() {
        List<CategoriesProduct> listCategoriesProducts = categorieProductService.retrieveAllCategoriesProducts();
        return listCategoriesProducts;

    }

    @GetMapping("/retrieve-categorieProducts/{categorieProducts-id}")
    public CategoriesProduct retrieveCategoriesProduct(@PathVariable("categorieProducts-id") Long categorieProductsId) {
        return categorieProductService.retrieveCategoriesProduct(categorieProductsId);
    }

    @PostMapping("/add-categorieProducts")
    public CategoriesProduct addCategoriesProduct(@RequestBody CategoriesProduct e) {
        CategoriesProduct categorieProducts = categorieProductService.addCategoriesProduct(e);
        return categorieProducts;
    }



    @PutMapping("/update-categorieProducts")
    public CategoriesProduct updateCategoriesProduct(@RequestBody CategoriesProduct e){
        CategoriesProduct categorieProducts = categorieProductService.updateCategoriesProduct(e);
        return categorieProducts;
    }
}
