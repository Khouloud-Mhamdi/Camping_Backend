package com.example.spring.camping.servicesImpl.ReservationServiceImpl;

import com.example.spring.camping.models.Activites.Activite;
import com.example.spring.camping.models.CampLocations.CampSite;
import com.example.spring.camping.models.Reservation.Check_In;
import com.example.spring.camping.models.Reservation.DetailReservation;
import com.example.spring.camping.models.Reservation.Reservation;
import com.example.spring.camping.respositories.ActiviteRepository.ActiviteRepository;
import com.example.spring.camping.respositories.CampSiteRepositories.CampsiteRepository;
import com.example.spring.camping.respositories.ReservationRepository.Check_InRepo;
import com.example.spring.camping.respositories.ReservationRepository.DetailRepository;
import com.example.spring.camping.respositories.ReservationRepository.ReservationRepository;
import com.example.spring.camping.services.ReservationService.IReservationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



@Service
@AllArgsConstructor
@Slf4j
public class ReservationService implements IReservationService {


    ReservationRepository reservationRepository;

    DetailRepository detailRepository;
    Check_InRepo checkInRepo;
   ActiviteRepository activiteRepository;
   CampsiteRepository campsiteRepository;

    @Override
    public Reservation addReservation(Reservation reservation) {
        DetailReservation detailReservation=reservation.getDetailReservation();
        detailRepository.save(detailReservation);
        return reservationRepository.save(reservation);

    }

    @Override
    public Reservation updateReservation(Long idReservation, Reservation newReservationDetails) {
        // Retrieve the reservation object from the repository using the ID
        Reservation reservation = reservationRepository.findById(idReservation).get();



        // Update reservation details
        reservation.setCampeurId(newReservationDetails.getCampeurId());
        reservation.setCampSite(newReservationDetails.getCampSite());

        // Retrieve the detail reservation associated with this reservation
        DetailReservation detailReservation = reservation.getDetailReservation();

        // Update detail reservation details
        detailReservation.setDateArrivee(newReservationDetails.getDetailReservation().getDateArrivee());
        detailReservation.setDateDepart(newReservationDetails.getDetailReservation().getDateDepart());
        detailReservation.setNombreCampeurs(newReservationDetails.getDetailReservation().getNombreCampeurs());

        detailReservation.setPrix(newReservationDetails.getDetailReservation().getPrix());

        // Save the updated reservation and detail reservation
        reservationRepository.save(reservation);
        detailRepository.save(detailReservation);
        return reservation;

    }


    @Override
    public void archiveReservation(Long idReservation) {
        Reservation reservation = reservationRepository.findById(idReservation).get();

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

        detailRepository.save(detailReservation);
        reservationRepository.save(reservation);
        return null;
    }

    @Override
    public String confirmReservation(Long idReservation) {

        Reservation reservation=reservationRepository.findById(idReservation).get();
        DetailReservation detailReservation=  reservation.getDetailReservation();


        return null;
    }

    @Override
    public boolean checkAvailability(Date startDate, Date endDate, long campsiteId) {

        CampSite campSite=campsiteRepository.findById(campsiteId).get();

        List<Date> reservationDates = getDatesBetween(startDate,endDate);
        for (Date date :reservationDates){
        for (Check_In checkIn:checkInRepo.findAll()){
            if (checkIn.getNbPlaceDispo()==0){

                return false;

            }
        }}


        return true;
    }

    @Override
    public Long idUserQuiaPlusDeReservation() {
        return null;
    }

    @Override
    public Long nbFutureReservations() {
        return null;
    }


    public static List<Date> getDatesBetween(Date startDate, Date endDate) {
        List<Date> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        while (!calendar.getTime().after(endDate)) {
            // Add the current date to the list
            dates.add(calendar.getTime());
            // Increment the calendar date by one day
            calendar.add(Calendar.DATE, 1);
        }

        // Adding endDate to the list if it's not already included
        if (!dates.contains(endDate)) {
            dates.add(endDate);
        }

        return dates;
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

    public int getOldReservations() {
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        int nb = reservationRepository.findOldReservation(date);

        return nb;
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
    private Long duree(Date startDate, Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();
        return diff / (1000 * 60 * 60 * 24);
    }

    @Override
    public Reservation reserver(Reservation reservation, long campsiteId,long campeurId) {
        // Save the reservation first to ensure it is not in a transient state


        Reservation savedReservation = addReservation(reservation);
        savedReservation.setCampeurId(campeurId);

        // Fetch the campsite by its ID
        CampSite campSite = campsiteRepository.findById(campsiteId).get();

        // Get the list of dates between the arrival and departure dates
        List<Date> reservationDates = getDatesBetween(savedReservation.getDetailReservation().getDateArrivee(), savedReservation.getDetailReservation().getDateDepart());
        Float prix =reservationDates.size()*campSite.getPrix()*savedReservation.getDetailReservation().getNombreCampeurs();
        savedReservation.getDetailReservation().setPrix(prix);
        // Iterate over each date in the reservation period
        for (Date date : reservationDates) {
            // Check if a check-in record already exists for the given date and campsite
            Check_In existingCheckIn = checkInRepo.findByDateAndAndCampSiteId(date, campsiteId);

            if (existingCheckIn == null) {
                // If no existing check-in, create a new check-in entry
                Check_In checkIn = new Check_In();
                checkIn.setCampSiteId(campsiteId);
                checkIn.setNbPlaceDispo(campSite.getPlaces() - savedReservation.getDetailReservation().getNombreCampeurs());
                checkIn.setDate(date);
                checkIn.setReservation(savedReservation);
                checkInRepo.save(checkIn);
                savedReservation.setCheck_in(checkIn);
            } else {
                // If there is an existing check-in, update the check-in entry
                if (existingCheckIn.getNbPlaceDispo()-savedReservation.getDetailReservation().getNombreCampeurs() < 0) {

                    reservationRepository.deleteById(savedReservation.getIdReservation()); // No available places for the given date
                    detailRepository.deleteById(savedReservation.getDetailReservation().getDetailResID());
                    return null;
                } else {
                    existingCheckIn.setNbPlaceDispo(existingCheckIn.getNbPlaceDispo() - savedReservation.getDetailReservation().getNombreCampeurs());
                    checkInRepo.save(existingCheckIn);
                    savedReservation.setCheck_in(existingCheckIn);
                }
            }
        }
        return savedReservation;
    }

    @Override
    public Reservation reserverActivite(List<Long> activiteListId, long campeurId,Reservation res ) {
        float prix=0;
        List<Activite> ListActivite= new ArrayList<>();
        List<Reservation> ress= new ArrayList<>();
        ress.add(res);
        addReservation(res);
        for (Long a : activiteListId){
            Activite activite = activiteRepository.findById(a).get();
            if (activite.getParticipants()-res.getDetailReservation().getNombreCampeurs()>0){
                ListActivite.add(activite);
                prix=activite.getPrix()+prix;

                activite.setListReservation(ress);
            }else {
                return  null;
            }

        }
        res.getDetailReservation().setPrix(prix);
        res.setActivites(ListActivite);
        res.setCampeurId(campeurId);
        Reservation reservation=addReservation(res);

        return reservation;
    }




}
