package com.example.spring.camping.servicesImpl.ReservationServiceImpl;

import com.example.spring.camping.models.Reservation.Reservation;
import com.example.spring.camping.respositories.ReservationRepository.ReservationRepository;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;


@Service
public class CsvService {

    @Autowired
    ReservationRepository repository;

    public void writeReservationsToCsv(Writer writer) {
        List<Reservation> reservations = repository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                .withHeader("id", "place","date_Arrivee","date_depart"))) {
            for (Reservation reservation : reservations) {
                csvPrinter.printRecord(
                        reservation.getIdReservation(),
                        reservation.getCampSite().getLieu(),
                        reservation.getDetailReservation().getDateArrivee(),
                        reservation.getDetailReservation().getDateDepart()
                );
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to write CSV data", e);
        }
    }
}
