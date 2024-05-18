package com.example.spring.camping.servicesImpl.ReservationServiceImpl;

import com.example.spring.camping.models.Reservation.DetailReservation;
import com.example.spring.camping.models.Reservation.Reservation;
import com.example.spring.camping.models.Reservation.Status;
import com.example.spring.camping.respositories.ReservationRepository.DetailRepository;
import com.example.spring.camping.respositories.ReservationRepository.ReservationRepository;
import com.example.spring.camping.services.ReservationService.IReservationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.spring.camping.models.Reservation.Status.IN_PROGRESS;

@Service
@AllArgsConstructor
@Slf4j
public class ReservationService implements IReservationService {


    ReservationRepository reservationRepository;

    DetailRepository detailRepository;




    @Override
    public Reservation addReservation(Reservation reservation) {
        DetailReservation detailReservation=reservation.getDetailReservation();
        detailReservation.setStatusReservation(IN_PROGRESS);
        detailRepository.save(detailReservation);
        return reservationRepository.save(reservation);

    }

    @Override
    public Reservation updateReservation(Long idReservation, Reservation newReservationDetails) {
        // Retrieve the reservation object from the repository using the ID
        Reservation reservation = reservationRepository.findById(idReservation).get();



        // Update reservation details
        reservation.setCampeurId(newReservationDetails.getCampeurId());
        reservation.setCampsiteId(newReservationDetails.getCampsiteId());

        // Retrieve the detail reservation associated with this reservation
        DetailReservation detailReservation = reservation.getDetailReservation();

        // Update detail reservation details
        detailReservation.setDateArrivee(newReservationDetails.getDetailReservation().getDateArrivee());
        detailReservation.setDateDepart(newReservationDetails.getDetailReservation().getDateDepart());
        detailReservation.setNombreCampeurs(newReservationDetails.getDetailReservation().getNombreCampeurs());
        detailReservation.setStatusReservation(newReservationDetails.getDetailReservation().getStatusReservation());
        detailReservation.setPrix(newReservationDetails.getDetailReservation().getPrix());

        // Save the updated reservation and detail reservation
        reservationRepository.save(reservation);
        detailRepository.save(detailReservation);
        return reservation;

    }


    @Override
    public void archiveReservation(Long idReservation) {
        Reservation reservation = reservationRepository.findById(idReservation).get();
        reservation.getDetailReservation().setStatusReservation(Status.CANCELLED);
    }

    @Override
    public Reservation getReservation(Long idReservation) {
        return reservationRepository.findById(idReservation).get();
    }

    @Override
    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }

    @Override
    public void deleteReservation(Long idReservation) {
        Reservation reservation = reservationRepository.findById(idReservation).get();
        Long iddetail=reservation.getDetailReservation().getDetailResID();
        reservationRepository.deleteById(idReservation);
        detailRepository.deleteById(iddetail);
    }


    @Override
    public String cancelReservation(Long idReservation) {
        Reservation reservation=reservationRepository.findById(idReservation).get();
        DetailReservation detailReservation=  reservation.getDetailReservation();
        detailReservation.setStatusReservation(Status.CANCELLED);
        detailRepository.save(detailReservation);
        reservationRepository.save(reservation);
        return detailReservation.getStatusReservation().toString();
    }

    @Override
    public String confirmReservation(Long idReservation) {

        Reservation reservation=reservationRepository.findById(idReservation).get();
        DetailReservation detailReservation=  reservation.getDetailReservation();
        detailReservation.setStatusReservation(Status.CONFIRMED);

        return detailReservation.getStatusReservation().toString();
    }

    @Override
    public boolean checkAvailability(Date startDate, Date endDate) {

        List<Reservation> overlappingReservations = reservationRepository.findOverlappingReservations(startDate, endDate);


        return overlappingReservations.isEmpty();
    }

    @Override
    public Long idUserQuiaPlusDeReservation() {
        return null;
    }

    @Override
    public Long nbFutureReservations() {
        return null;
    }



    @Override
    public List<Reservation> getReservationsByStartDate() {
        List<Reservation> list = new ArrayList<>();
        int currentMonth = LocalDate.now().getMonth().getValue();
        List<Reservation> listRes = reservationRepository.findAll();

        for (Reservation reservation : listRes) {

            int m=reservation.getDetailReservation().getDateArrivee().getMonth();

            if (m==currentMonth){
                System.out.println(m);

                list.add(reservation);
            }


        }

        return list;
    }



    @Override
    public int getUser() {
        return reservationRepository.findUsers();
    }
    @Override
    public int getCampsite() {
        return reservationRepository.findcampsites();
    }


    public int getFutureReservations() {
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        int nb = reservationRepository.findFutureReservation(date);

        return nb;
    }

    @Override
    public int nbrReservationInprogress() {
        return reservationRepository.findCountInprogress();
    }

    @Override
    public List<Integer> getNbrReservationByMonth() {
        List<Integer> data = new ArrayList<>();
        List<Object[]> results = reservationRepository.getNbrReservationByMonth();

        for (int i = 0; i < 12; i++) {
            data.add(0);
        }


        for (Object[] result : results) {
            int month = (int) result[0];
            int count = ((Number) result[1]).intValue();
            data.set(month - 1, count);
        }

        System.out.println(data);
        return data;
    }

}
