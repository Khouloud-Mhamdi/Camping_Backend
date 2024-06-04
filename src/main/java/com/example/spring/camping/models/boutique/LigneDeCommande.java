package com.example.spring.camping.models.boutique;

import com.example.spring.camping.models.EtatCommande;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LigneDeCommande implements Serializable {
    @EmbeddedId
    private LigneDeCommandeId idLigneDeCommande;
    private int quantiter;
    private Long prix_Unitaire;
    @Enumerated(EnumType.STRING)
    private EtatCommande etat_LigneDeCommande;
}
