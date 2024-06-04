package com.example.spring.camping.controllers.ReservationController;

import com.example.spring.camping.servicesImpl.ReservationServiceImpl.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
public class CsvExportController {

    @Autowired
    private CsvService csvService;

    @GetMapping("/export-reservations")
    public void exportReservations(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=reservations.csv");
        PrintWriter writer = response.getWriter();
        csvService.writeReservationsToCsv(writer);
    }
}
