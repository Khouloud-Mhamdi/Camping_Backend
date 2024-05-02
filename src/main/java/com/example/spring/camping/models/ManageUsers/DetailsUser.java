package com.example.spring.camping.models.ManageUsers;

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

    private String paysage ;
    private String Couleur ;
    private String alimentation ;
    private  String musique ;
    private String compagnement ;
    private  String saison;
    private String type_hebergement;

    @OneToOne (mappedBy = "detailsUser")
    private User user;

}
