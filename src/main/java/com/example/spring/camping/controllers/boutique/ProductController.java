package com.example.spring.camping.controllers.boutique;


import com.example.spring.camping.models.boutique.Product;
import com.example.spring.camping.services.boutique.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    IProductService productService;

    @GetMapping("/retrieve-all-products")
    public List<Product> getProducts() {
        List<Product> listProducts = productService.retrieveAllProduct();
        return listProducts;

    }

    @GetMapping("/retrieve-products/{products-id}")
    public Product retrieveProduct(@PathVariable("products-id") Long productsId) {
        return productService.retrieveProduct(productsId);
    }

    @PostMapping("/add-products")
    public Product addProduct(@RequestBody Product e) {
        Product products = productService.addProduct(e);
        return products;
    }



    @PutMapping("/update-products")
    public Product updateProduct(@RequestBody Product e){
        Product products = productService.updateProduct(e);
        return products;
    }
}
