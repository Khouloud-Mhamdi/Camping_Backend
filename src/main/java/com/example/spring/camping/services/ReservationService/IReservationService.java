package com.example.spring.camping.services.ReservationService;

import com.example.spring.camping.models.Reservation.Reservation;

import java.util.Date;
import java.util.List;

public interface IReservationService {
    public Reservation addReservation(Reservation reservation);
    public Reservation updateReservation(Long idReservation, Reservation newReservationDetails) ;
    public void archiveReservation(Long idReservation);

    public Reservation getReservation(Long idReservation);
    public List<Reservation> getAllReservation();

    public void deleteReservation(Long idReservation);




    public String cancelReservation(Long idReservation);
    public String confirmReservation(Long idReservation);
    public boolean checkAvailability(Date startDate, Date endDate);


    public Long idUserQuiaPlusDeReservation();
    public Long nbFutureReservations();



    List<Reservation> getReservationsByStartDate();
    public int getCampsite();
    public int getUser();
    public int getFutureReservations();
    public int nbrReservationInprogress();


    List<Integer> getNbrReservationByMonth();
}
