package com.java.firebase.service.Patient;

import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.java.firebase.model.Doctor.Doctor;
import com.java.firebase.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
            String patientUid = UserService.getInstance().globalUid;

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

    public void finishAppointment(String doctorUid, String time, String diagnosis) throws ExecutionException, InterruptedException {
        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            String patientUid = UserService.getInstance().globalUid;

            // Get doctor database
            DocumentReference docRef = dbFirestore.collection("Doctors").document(doctorUid);
            DocumentSnapshot doctorSnapshot = docRef.get().get();
            String doctorName = doctorSnapshot.getString("fullName");

            // Get patient database
            DocumentReference patientRef = dbFirestore.collection("Patients").document(patientUid);
            DocumentSnapshot patientSnapshot = patientRef.get().get();
            String patientName = patientSnapshot.getString("fullName");

            // remove booking from patient's upcoming appointments
            patientRef.update("patientUpcomingAppointments", FieldValue.arrayRemove(getPatientAppointmentData(patientUid, doctorUid, time))).get();

            // add booking to patient's appointments history
            Map<String, Object> appointmentData = new HashMap<>();
            appointmentData.put("doctorName", doctorName);
            appointmentData.put("diagnosis", diagnosis);
            patientRef.update("patientAppointmentHistory", FieldValue.arrayUnion(appointmentData)).get();

            // remove booking from doctor's upcoming appointments
            docRef.update("doctorUpcomingAppointments", FieldValue.arrayRemove(getDoctorAppointmentData(patientUid, doctorUid, time))).get();

            // add booking to doctor's appointments history
            Map<String, Object> patientData = new HashMap<>();
            patientData.put("patientName", patientName);
            patientData.put("diagnosis", diagnosis);
            docRef.update("doctorAppointmentHistory", FieldValue.arrayUnion(patientData)).get();
        } catch (InterruptedException | ExecutionException | NullPointerException e) {
            e.printStackTrace();
            throw new RuntimeException("Error finishing appointment", e);
        }
    }

    private Map<String, String> getPatientAppointmentData(String patientUid, String doctorUid, String time) throws ExecutionException, InterruptedException {
        Firestore checkFirestore = FirestoreClient.getFirestore();
        DocumentReference checkRef = checkFirestore.collection("Patients").document(patientUid);
        DocumentSnapshot checkSnapshot = checkRef.get().get();

        if (checkSnapshot.exists()) {
            if (checkSnapshot.contains("patientUpcomingAppointments")) {
                List<Map<String, String>> patientAppointments = (List<Map<String, String>>) checkSnapshot.get("patientUpcomingAppointments");
                for (Map<String, String> appointment : patientAppointments) {
                    if (appointment.get("doctorUid").equals(doctorUid) && appointment.get("time").equals(time)) {
                        return appointment;
                    }
                }
            } else {
                throw new RuntimeException("Error getting patient upcoming appointments");
            }
        } else {
            throw new RuntimeException("patient snapshot doesnt exist");
        }
        return null;
    }

    private Map<String, String> getDoctorAppointmentData(String patientUid, String doctorUid, String time) throws ExecutionException, InterruptedException {
        Firestore checkFirestore = FirestoreClient.getFirestore();
        DocumentReference checkRef = checkFirestore.collection("Doctors").document(doctorUid);
        DocumentSnapshot checkSnapshot = checkRef.get().get();

        if (checkSnapshot.exists()) {
            if (checkSnapshot.contains("doctorUpcomingAppointments")) {
                List<Map<String, String>> doctorAppointments = (List<Map<String, String>>) checkSnapshot.get("doctorUpcomingAppointments");
                for (Map<String, String> appointment : doctorAppointments) {
                    if (appointment.get("patientUid").equals(patientUid) && appointment.get("time").equals(time)) {
                        return appointment;
                    }
                }
            } else {
                throw new RuntimeException("Error getting doctor upcoming appointments");
            }
        } else {
            throw new RuntimeException("doctor snapshot doesnt exist");
        }
        return null;
    }
}
