package com.example.spring.camping.models.ManageUsers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DetailsUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_DetailsUser;

    private TypePaysage paysage ;
    private String Couleur ;
    private Alimentation alimentation ;

    private Accompagnement compagnement ;
    private  Saison saison;
    private Hebergement type_hebergement;
    
    private Boolean status ;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

}
