package com.example.spring.camping.services.boutique;


import com.example.spring.camping.models.boutique.Product;

import java.util.List;

public interface IProductService {
    List<Product> retrieveAllProduct();
    Product addProduct(Product f);
    Product updateProduct(Product f);
    Product retrieveProduct(Long idProduct);


}
