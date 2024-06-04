
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


    //cloudinary part

//cloudinary part

    String imageUrl;
    String imageId;
    String name;

    @ManyToOne
    private DetailCampSite detailCampSites;


    public Photo(String original_filename, String url, String public_id) {
        this.imageId=public_id;
        this.imageUrl=url;
        this.name=original_filename;
    }
}
