package com.example.spring.camping.respositories.boutique;

import com.example.spring.camping.models.boutique.CategoriesProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesProductRepository extends JpaRepository<CategoriesProduct,Long> {
}
