package com.example.spring.camping.respositories.boutique;

import com.example.spring.camping.models.boutique.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByNomProduct(String product);
}
