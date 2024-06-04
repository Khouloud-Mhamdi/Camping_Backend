package com.example.spring.camping.models.boutique;

import com.example.spring.camping.models.TypeProduct;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Commande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Commande;
    @Temporal(TemporalType.DATE)
    private Date date_Commande;
    private Long total_Commande;
    @Enumerated(EnumType.STRING)
    private TypeProduct type_Commande;
    @Temporal(TemporalType.DATE)
    private Date dateDeRetour;
    private String paymentMethode;
    private String payment_token;
    private long transaction;
    private Long idUtilisateur;
    @OneToMany(mappedBy = "idProduct",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> products;
}
