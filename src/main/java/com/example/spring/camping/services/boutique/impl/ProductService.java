package com.example.spring.camping.services.boutique.impl;


import com.example.spring.camping.models.boutique.Product;
import com.example.spring.camping.respositories.boutique.ProductRepository;
import com.example.spring.camping.services.boutique.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {
    ProductRepository productRepository;
    @Override
    public List<Product> retrieveAllProduct() {
        return productRepository.findAll().stream().filter(product -> !product.isArchiver()).collect(Collectors.toList());
    }

    @Override
    public Product addProduct(Product f) {
        return productRepository.save(f);
    }

    @Override
    public Product updateProduct(Product f) {
        return productRepository.save(f);
    }

    @Override
    public Product retrieveProduct(Long idProduct) {
        return productRepository.findById(idProduct).orElse(null);
    }

    @Override
    public void deleteProduct(Long idProduct) {
        Product product = productRepository.getById(idProduct);
        product.setArchiver(true);
        productRepository.save(product);
    }
}
