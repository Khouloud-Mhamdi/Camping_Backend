package com.example.spring.camping.models.Activites;

import com.example.spring.camping.models.Activites.Activite;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;


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
