package com.example.spring.camping.controllers.ReservationController;

import com.example.spring.camping.models.Reservation.Reservation;
import com.example.spring.camping.services.ReservationService.IReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/projet")
public class ReservationController {

    IReservationService reservationService;
    @PostMapping("/addReservation")
    @ResponseBody
    public Reservation addReservation(@RequestBody Reservation reservation) {
        Reservation reservation1 = reservationService.addReservation(reservation);
        return reservation1;
    }


    @GetMapping("/getReservation/{id}")
    @ResponseBody
    public  Reservation getReservation(@PathVariable("id") Long idReservation) {
        return reservationService.getReservation(idReservation);
    }

    @PutMapping("/updateReservation/{id}")
    @ResponseBody
    public  Reservation updateReservation(@PathVariable("id") Long idReservation,@RequestBody Reservation newReservationDetails)  {
        return reservationService.updateReservation(idReservation,newReservationDetails);
    }
    @PutMapping("/archiveReservation/{id}")
    @ResponseBody
    public void archiveReservation(@PathVariable("id") Long idReservation) {
        reservationService.archiveReservation(idReservation);
    }
    @GetMapping("/getAll")
    @ResponseBody
    public List<Reservation> getAllReservation() {
        return reservationService.getAllReservation();
    }

    @DeleteMapping("/deleteReservation/{id}")
    @ResponseBody
    public void deleteReservation(@PathVariable("id") Long idReservation) {
        reservationService.deleteReservation (idReservation);
    }
    @PutMapping("/cancel/{idReservation}")
    public ResponseEntity<String> cancelReservation(@PathVariable Long idReservation) {
        String status = reservationService.cancelReservation(idReservation);
        return ResponseEntity.ok(status);
    }

    @PutMapping("/confirm/{idReservation}")
    public ResponseEntity<String> confirmReservation(@PathVariable Long idReservation) {
        String status = reservationService.confirmReservation(idReservation);
        return ResponseEntity.ok(status);
    }


    @GetMapping("/reservations/overlapping/{startDate}/{endDate}")
    public boolean findReservations(
            @PathVariable("startDate") Date startDate,
            @PathVariable("endDate") Date endDate) {
        return reservationService.checkAvailability(startDate,endDate);
    }




    @GetMapping("/reservations/getReservationsByStartDate")
    List<Reservation> getReservationsByStartDate(){

        return reservationService.getReservationsByStartDate();
    }



    @GetMapping("/reservations/getFutureReservations")
    int getFutureReservations(){

        return reservationService.getFutureReservations();
    }



    @GetMapping("/reservations/getUser")
    public int getUser(){

        return reservationService.getUser();
    }

    @GetMapping("/reservations/nbrReservationInprogress")
    public int nbrReservationInprogress(){

        return reservationService.nbrReservationInprogress();
    }
    @GetMapping("/reservations/findcampsites")
    public int findcampsites(){
        return reservationService.getCampsite();
    }
    @GetMapping("/reservations/getNbrReservationByMonth")
    public List<Integer> getNbrReservationByMonth(){
        return reservationService.getNbrReservationByMonth();
    }
}


