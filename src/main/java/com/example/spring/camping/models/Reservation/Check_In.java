package com.example.spring.camping.models.Reservation;

import com.example.spring.camping.models.CampLocations.CampSite;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Check_In implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCheck;
    private Date date;
    private Long nbPlaceDispo;
    @OneToOne(mappedBy = "check_in")
    Reservation reservation;
    @OneToOne(mappedBy = "checkIn")
    CampSite campSite;

}
