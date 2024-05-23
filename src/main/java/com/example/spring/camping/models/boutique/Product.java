package com.example.spring.camping.models.boutique;

import com.example.spring.camping.models.CampLocations.Photo;
import com.example.spring.camping.models.ManageUsers.Alimentation;
import com.example.spring.camping.models.TypeProduct;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;
    @Column(unique = true)
    private String nomProduct;
    private String description;
    @Enumerated(EnumType.STRING)
    private TypeProduct product_Type;
    private int quantiter;
    private long prix;

    @ManyToOne
    private CategoriesProduct id_Categorie;
    @OneToMany(mappedBy = "id_Commande")
    @JsonIgnore
    private List<Commande> commandes;
    @OneToMany(mappedBy = "produit")
    @JsonIgnore
    private List<Panier> paniers ;
    @OneToOne(mappedBy = "product")
    private Photo photo;
}
