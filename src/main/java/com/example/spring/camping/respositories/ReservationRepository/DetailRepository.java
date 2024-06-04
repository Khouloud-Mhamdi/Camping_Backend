package com.example.spring.camping.respositories.ReservationRepository;

import com.example.spring.camping.models.Reservation.DetailReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface DetailRepository extends JpaRepository<DetailReservation,Long> {

    DetailReservation findByDateArrivee(Date dateArrivee);
}
