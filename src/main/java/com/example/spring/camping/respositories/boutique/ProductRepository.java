package com.example.spring.camping.respositories.boutique;

import com.example.spring.camping.models.TypeProduct;
import com.example.spring.camping.models.boutique.CategoriesProduct;
import com.example.spring.camping.models.boutique.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p FROM Product p WHERE p.nomProduct LIKE %:productName%")
    List<Product> findByNomProduct(@Param("productName") String productName);

    @Query("SELECT p FROM Product p WHERE p.id_Categorie = :category")
    List<Product> findByCategory(@Param("category") CategoriesProduct category);





}
