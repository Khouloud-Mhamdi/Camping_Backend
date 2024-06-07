package com.example.spring.camping.models.Reservation;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DetailReservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailResID;
    @DateTimeFormat(pattern = "DD/MM/YYYY")
    private Date dateArrivee;
    @DateTimeFormat(pattern = "DD/MM/YYYY")
    private Date dateDepart;
    private Long nombreCampeurs;


    private float prix;
    @OneToOne(mappedBy = "detailReservation")
    @JsonIgnore
    Reservation reservation;



}

