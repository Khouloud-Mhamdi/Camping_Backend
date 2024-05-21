package com.example.spring.camping.respositories.ReservationRepository;

import com.example.spring.camping.models.Reservation.DetailReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailRepository extends JpaRepository<DetailReservation,Long> {

}
