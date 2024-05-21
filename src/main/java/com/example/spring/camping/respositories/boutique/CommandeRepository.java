package com.example.spring.camping.respositories.boutique;

import com.example.spring.camping.models.boutique.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande,Long> {
}
