package com.java.firebase.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.SetOptions;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.java.firebase.model.BookingDetails;
import com.java.firebase.model.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class BookingService {

    private final DoctorSearchService doctorSearchService;

    public BookingService(DoctorSearchService doctorSearchService) {
        this.doctorSearchService = doctorSearchService;
    }

    public String bookAppointment(String doctorUid, String time) throws ExecutionException, InterruptedException {
        Doctor doctor = doctorSearchService.getDoctorDetails(doctorUid);
        if (doctor != null) {
            // Create a new booking object
            BookingDetails booking = new BookingDetails(time);

            // Add the booking to the doctor's list of bookings
            doctor.getBookings().add(booking);

            // Update the doctor's availability
            doctor.getAppointmentTimes().remove(time);

            // Save the updated doctor information back to the database
            Firestore dbFirestore = FirestoreClient.getFirestore();
            DocumentReference docRef = dbFirestore.collection("Doctors").document(doctorUid);
            ApiFuture<WriteResult> future = docRef.set(doctor, SetOptions.merge());
            future.get();
            return "Appointment booked successfully.";
        } else {
            return "Doctor not found or booking failed.";
        }
    }
}
