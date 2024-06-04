package com.example.spring.camping.models.Reclamation;
import java.sql.Date;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Suivi_Reclamation")
public class SuiviReclamation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_SuiviRec")
    private long idSuiviRec;

    @Column(name = "id_Reclamation")
    private long idReclamation;

    @Column(name = "Date_SuiviRec")
    private Date dateSuiviRec;

    @Column(name = "Desc_suivi")
    private String descSuivi;

    @Column(name = "ByWho")
    private Long ByWho;

}
