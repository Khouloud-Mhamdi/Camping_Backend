package com.example.spring.camping.respositories.boutique;

import com.example.spring.camping.models.boutique.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByNomProduct(String product);

    @Query("SELECT sum(pd.idLigneDeCommande.product.prixdachat)  FROM Commande c,LigneDeCommande pd WHERE pd.idLigneDeCommande.commande.id_Commande=c.id_Commande" )
    float getSumPrixdachat();

    @Query("SELECT sum(pd.idLigneDeCommande.product.prix)  FROM Commande c,LigneDeCommande pd WHERE pd.idLigneDeCommande.commande.id_Commande=c.id_Commande" )
    float getSumPrixdvente();
}
