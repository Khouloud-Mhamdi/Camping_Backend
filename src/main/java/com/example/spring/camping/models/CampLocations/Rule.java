package com.example.spring.camping.models.CampLocations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Rule {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ruleId;
    Long numero;
    String description;

/*
    @ManyToMany(mappedBy = "rules",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Set <CampSite> campSites;
*/
    @ManyToOne
        @JsonIgnore
    CampSite campSite;


}
