package com.java.firebase.controllers.Patient;

import com.java.firebase.model.Doctor.AppointmentDetails;
import com.java.firebase.model.Patient.CovidSurvey;
import com.java.firebase.service.Patient.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
@RestController
@CrossOrigin
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/bookAppointment")
    public boolean bookAppointment(@RequestParam String doctorUid, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, @RequestParam String time, @RequestBody CovidSurvey covidSurvey) throws ExecutionException, InterruptedException {
        return bookingService.bookAppointment(doctorUid, date, time, covidSurvey.getExperiencedSymptoms(), covidSurvey.getClosePhysicalContact(), covidSurvey.getPositiveCovid90Days(), covidSurvey.getSelfMonitor(), covidSurvey.getWantCovidTest());
    }

    @PostMapping("/finishAppointment")
    public void finishAppointment(@RequestParam String doctorUid, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, @RequestParam String time, @RequestBody AppointmentDetails appointmentDetails) throws ExecutionException, InterruptedException {
        bookingService.finishAppointment(doctorUid, date, time, appointmentDetails.getDiagnosis(),
                appointmentDetails.getCovidSymptomDetails(), appointmentDetails.getTestResults(),
                appointmentDetails.getMedicalHistory(), appointmentDetails.getInsuranceDetails());
    }

    @GetMapping("/checkAvailability")
    public List<String> checkAvailability(@RequestParam String doctorUid, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) throws ExecutionException, InterruptedException {
        return bookingService.checkAvailability(doctorUid, date);
    }

}
