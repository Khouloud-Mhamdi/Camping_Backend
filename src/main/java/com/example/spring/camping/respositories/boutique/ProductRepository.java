package com.example.spring.camping.respositories.boutique;

import com.example.spring.camping.models.TypeProduct;
import com.example.spring.camping.models.boutique.CategoriesProduct;
import com.example.spring.camping.models.boutique.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByNomProduct(String product);

    @Query("SELECT sum(pd.idLigneDeCommande.product.prixdachat)  FROM Commande c,LigneDeCommande pd WHERE pd.idLigneDeCommande.commande.id_Commande=c.id_Commande And c.type_Commande=:typeproduct" )
    float getSumPrixdachat(TypeProduct typeproduct);
    @Query("SELECT sum(pd.idLigneDeCommande.product.prixdachat)  FROM Commande c,LigneDeCommande pd WHERE pd.idLigneDeCommande.commande.id_Commande=c.id_Commande" )
    float getSumPrixdachat();
    @Query("SELECT sum(pd.idLigneDeCommande.product.prix)  FROM Commande c,LigneDeCommande pd WHERE pd.idLigneDeCommande.commande.id_Commande=c.id_Commande And c.type_Commande=:typeproduct" )
    float getSumPrixdvente(TypeProduct typeproduct);

    @Query("SELECT sum(pd.idLigneDeCommande.product.prix)  FROM Commande c,LigneDeCommande pd WHERE pd.idLigneDeCommande.commande.id_Commande=c.id_Commande " )
    float getSumPrixdvente();






    @Query("SELECT p FROM Product p WHERE p.nomProduct LIKE %:productName%")
    List<Product> findByNomProductSearch(@Param("productName") String productName);

    @Query("SELECT p FROM Product p WHERE p.id_Categorie = :category")
    List<Product> findByCategory(@Param("category") CategoriesProduct category);

}
