        package com.example.spring.camping.models.CampLocations;


import com.example.spring.camping.models.CampLocations.Rule;
import com.example.spring.camping.models.Paysage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
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

    @Enumerated(EnumType.STRING)
    Paysage paysage;
    @ManyToMany
    Set<Rule>rules;

    @OneToOne
            @JsonIgnore
    DetailCampSite detailCampSite;



}
