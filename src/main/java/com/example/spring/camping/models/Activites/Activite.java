package com.example.spring.camping.models.Activites;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Activite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ActiviteID;

    private String NomActivite;
    private String Description;
    private String Date;
    private long Participants;
    private String Lieu;
    private boolean Status;
    @Enumerated(EnumType.STRING)
    private Pre_requis Pre_requis;
    private float prix;

    //@OneToMany(mappedBy = activite)
    //private List<Campsite> campsites;

    @ManyToOne
    public CategorieActivite categorieactivite;

}