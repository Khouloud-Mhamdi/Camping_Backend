package com.example.spring.camping.respositories.ReservationRepository;

import com.example.spring.camping.models.Reservation.Check_In;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface Check_InRepo extends JpaRepository<Check_In,Long> {


    Check_In findByDateAndAndCampSiteId(Date date, long campsiteId);
}
