package com.example.spring.camping.models.boutique;

import com.example.spring.camping.models.ManageUsers.User;
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
public class Panier implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idPanier;
    private int quantiter;
    private long prixUnitaire;

    @OneToOne (mappedBy = "panier")
    private User user;

    @ManyToOne
    private Product produit;
}
