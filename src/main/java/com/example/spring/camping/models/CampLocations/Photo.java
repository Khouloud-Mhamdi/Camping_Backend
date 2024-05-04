        package com.example.spring.camping.models.CampLocations;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

        @Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)


public class Photo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long photoId;
    byte[] image;


    @ManyToOne
    private DetailCampSite detailCampSites;

}

