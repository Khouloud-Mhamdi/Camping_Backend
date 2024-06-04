package com.example.spring.camping.models.CampLocations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long detailcampid;
    String description;
    String coordonne;
    int duree;
    LocalTime heureDepart;

    @OneToMany(mappedBy = "detailCampSites")
          //hedhi tetnaha
            //@JsonIgnore
    Set<Photo> photos;

    @OneToOne(mappedBy = "detailCampSite")
            @JsonIgnore
    CampSite campSite;

}
