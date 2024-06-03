package com.example.spring.camping.respositories.boutique;

import com.example.spring.camping.models.TypeProduct;
import com.example.spring.camping.models.boutique.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface CommandeRepository extends JpaRepository<Commande,Long> {
    @Query("SELECT SUM(s.total_Commande) FROM Commande s WHERE  s.type_Commande=:typeC AND s.date_Commande BETWEEN :startDate AND :endDate")
    Double findTotalCommandeBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate,@Param("typeC") TypeProduct typeC);

    @Query("SELECT COUNT(s) FROM Commande s WHERE s.type_Commande=:typeC AND s.date_Commande BETWEEN :startDate AND :endDate")
    Long countCpmmandeBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate,@Param("typeC") TypeProduct typeC);

}
