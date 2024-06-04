package com.example.spring.camping.respositories.ReservationRepository;

import com.example.spring.camping.models.Reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    @Query("SELECT r FROM Reservation r WHERE (:endDate >= r.detailReservation.dateArrivee AND :startDate <= r.detailReservation.dateDepart)")
    List<Reservation> findOverlappingReservations(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT Count(r) FROM Reservation r WHERE (:startDate <= r.detailReservation.dateArrivee)")
    int findFutureReservation(@Param("startDate") Date startDate);

    @Query("SELECT Count(r) FROM Reservation r WHERE (:endDate >= r.detailReservation.dateDepart)")
    int findOldReservation(@Param("endDate") Date endDate);
    @Query("SELECT count(DISTINCT r.campeurId) FROM Reservation r ")
    int findUsers();

    @Query("SELECT count(DISTINCT r.campSite) FROM Reservation r ")
    int findcampsites();


    @Query("SELECT MONTH(r.detailReservation.dateArrivee), COUNT(r) FROM Reservation r GROUP BY MONTH(r.detailReservation.dateArrivee)")
    List<Object[]> getNbrReservationByMonth();

}
