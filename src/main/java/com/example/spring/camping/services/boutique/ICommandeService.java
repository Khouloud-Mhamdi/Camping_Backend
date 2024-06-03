package com.example.spring.camping.services.boutique;


import com.example.spring.camping.models.TypeProduct;
import com.example.spring.camping.models.boutique.Commande;

import java.util.Date;
import java.util.List;

public interface ICommandeService {
    List<Commande> retrieveAllCommandes();
    Commande addCommande(Commande f);
    Commande addSingleRentedCommande(Commande f,Long id);
    Commande updateCommande(Commande f);
    Commande retrieveCommande(Long idCommande);
    void deleteCommande(Long idCommande);
    float getProfit();
    Double findTotalSalesBetweenDates(Date startDate, Date endDate, TypeProduct t);

    Long countSalesBetweenDates(Date startDate,Date endDate, TypeProduct t);

}
