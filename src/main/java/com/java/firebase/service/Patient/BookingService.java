package com.java.firebase.service.Patient;

import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class BookingService {

    private final DoctorSearchService doctorSearchService;

    public BookingService(DoctorSearchService doctorSearchService) {
        this.doctorSearchService = doctorSearchService;
    }

    public boolean bookAppointment(String doctorUid, String time) {
        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            // Real patient uid = UserService.getInstance().getGlobalUid()
            String patientUid = "4Xyi0L0FpSbw657wfQVSDONt1QX2";

            // Get doctor database
            DocumentReference docRef = dbFirestore.collection("Doctors").document(doctorUid);
            DocumentSnapshot doctorSnapshot = docRef.get().get();
            String doctorName = doctorSnapshot.getString("fullName");

            // Get patient database
            DocumentReference patientRef = dbFirestore.collection("Patients").document(patientUid);
            DocumentSnapshot patientSnapshot = patientRef.get().get();
            String patientName = patientSnapshot.getString("fullName");

            // add booking to patient's upcoming appointments
            Map<String, Object> appointmentData = new HashMap<>();
            appointmentData.put("doctorName", doctorName);
            appointmentData.put("doctorUid", doctorUid);
            appointmentData.put("time", time);
            patientRef.update("patientUpcomingAppointments", FieldValue.arrayUnion(appointmentData)).get();

            // add booking to doctor's upcoming appointments
            Map<String, Object> patientData = new HashMap<>();
            patientData.put("patientName", patientName);
            patientData.put("patientUid", patientUid);
            patientData.put("time", time);
            docRef.update("doctorUpcomingAppointments", FieldValue.arrayUnion(patientData)).get();

            // remove booking from doctor's available times
            docRef.update("appointmentTimes", FieldValue.arrayRemove(time)).get();
            return true;
        } catch (InterruptedException | ExecutionException | NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }
}
