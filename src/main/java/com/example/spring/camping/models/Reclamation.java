package com.example.spring.camping.models;
import java.sql.Date;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Reclamation")
public class Reclamation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Reclamation")
    private long idReclamation;

    @Column(name = "title")
    private String title;

    @Column(name = "ID_Client")
    private long idClient;

    @Column(name = "Date_Reclamation")
    private Date dateReclamation;

    @Column(name = "Description_Reclamation")
    private String descriptionReclamation;

    @Column(name = "Statut_Reclamation")
    private EStatusReclamation statutReclamation;

    @Column(name = "Date_Resolution")
    private Date dateResolution;

    @Column(name = "Reponse")
    private String reponse;

}