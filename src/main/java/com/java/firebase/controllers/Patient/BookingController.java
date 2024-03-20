package com.java.firebase.controllers.Patient;

import com.java.firebase.service.Patient.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
@RestController
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
