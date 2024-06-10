package com.example.spring.camping.models.Activites;

import com.example.spring.camping.models.Reservation.Reservation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


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
    private LocalDate Date;
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
    @JsonIgnore
    @ManyToMany
    private List<Reservation> ListReservation;

}
