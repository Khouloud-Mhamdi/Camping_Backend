package com.example.spring.camping.models.Activites;

import com.example.spring.camping.models.Activites.Activite;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategorieActivite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long IDCategorieActivite;
    private long NomCategorie;


    @OneToMany
    private List<Activite> activites;

}
