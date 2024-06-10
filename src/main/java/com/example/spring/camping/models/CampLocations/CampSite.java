

package com.example.spring.camping.models.CampLocations;

import com.example.spring.camping.models.CampLocations.Rule;
import com.example.spring.camping.models.ManageUsers.TypePaysage;
import com.example.spring.camping.models.Reservation.Check_In;
import com.example.spring.camping.models.Reservation.Reservation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

import java.util.List;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class CampSite {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long campsiteid;
    String lieu;
    String region;
    float prix;
    int places;
    LocalDate date_prevu;
    boolean isArchived;


  /*  @Enumerated(EnumType.STRING)
    TypePaysage paysage;*/

    @Enumerated(EnumType.STRING)
    TypePaysage paysage;



  /**  @ManyToMany  (cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Rule>rules;**/


  @Enumerated(EnumType.STRING)
  Status status;

    @OneToMany(mappedBy = "campSite")
    Set<Rule> rules;

    @OneToOne
            //hedhi tetnaha
    //@JsonIgnore
    DetailCampSite detailCampSite;

    long id_user;

    @OneToMany(mappedBy = "campSite")
    List<Reservation> reservationList;



}





