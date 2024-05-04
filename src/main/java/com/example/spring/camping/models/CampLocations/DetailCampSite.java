package com.example.spring.camping.models.CampLocations;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalTime;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class DetailCampSite {



    @Id
    Long detailcampid;
    String description;
    String coordonne;
    int duree;
    LocalTime heureDepart;


    @OneToMany(mappedBy = "detailCampSites")
    Set<Photo> photos;

    @OneToOne(mappedBy = "detailCampSite")
    CampSite campSite;

}
