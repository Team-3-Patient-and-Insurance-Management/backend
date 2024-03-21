package com.java.firebase.controllers.Patient;

import com.java.firebase.service.Patient.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public boolean bookAppointment(@RequestParam String doctorUid, @RequestParam String time) throws ExecutionException, InterruptedException {
        return bookingService.bookAppointment(doctorUid, time);
    }

    @PostMapping("/finishAppointment")
    public void finishAppointment(@RequestParam String doctorUid, @RequestParam String time, @RequestBody String diagnosis) throws ExecutionException, InterruptedException {
        bookingService.finishAppointment(doctorUid, time, diagnosis);
    }


}
