
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
    boolean status;
    int places;
    LocalDate date_prevu;
    boolean isArchived;


  /*  @Enumerated(EnumType.STRING)
    TypePaysage paysage;*/

    @Enumerated(EnumType.STRING)
    TypePaysage paysage;

    @ManyToMany
    Set<Rule>rules;

    @OneToOne

    @JsonIgnore
    DetailCampSite detailCampSite;
    @OneToMany(mappedBy = "campSite",cascade = CascadeType.ALL)
    List<Reservation> reservationList;
    @OneToOne
    Check_In checkIn;


}





