package com.example.spring.camping.models.Reservation;




import com.example.spring.camping.models.Activites.Activite;

import com.example.spring.camping.models.CampLocations.CampSite;
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
@NoArgsConstructor
@AllArgsConstructor
public class Reservation implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;
    private Long campeurId;



    @OneToOne
    DetailReservation detailReservation;

    @OneToMany(fetch = FetchType.EAGER , mappedBy = "reservation")
    private List<Activite> activites;


    @JsonIgnore
    @ManyToOne
    CampSite campSite;


    @OneToOne
    Check_In check_in;


}

