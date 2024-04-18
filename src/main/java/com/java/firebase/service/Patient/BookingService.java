package com.java.firebase.service.Patient;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.java.firebase.model.Doctor.Doctor;
import com.java.firebase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class BookingService {

    @Autowired
    private JavaMailSender javaMailSender;

    private final DoctorSearchService doctorSearchService;

    public BookingService(DoctorSearchService doctorSearchService) {
        this.doctorSearchService = doctorSearchService;
    }

    public boolean bookAppointment(String doctorUid, Date date, String time, String experiencedSymptoms, String closePhysicalContact, String positiveCovid90Days, String selfMonitor, String wantCovidTest) {
        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            String patientUid = UserService.getGlobalUid();
            String patientEmail = UserService.getGlobalEmail();

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
            appointmentData.put("date", date);
            appointmentData.put("time", time);
            patientRef.update("patientUpcomingAppointments", FieldValue.arrayUnion(appointmentData)).get();

            // add booking to doctor's upcoming appointments
            Map<String, Object> patientData = new HashMap<>();
            patientData.put("patientName", patientName);
            patientData.put("patientUid", patientUid);
            patientData.put("date", date);
            patientData.put("time", time);
            patientData.put("experiencedSymptoms", experiencedSymptoms);
            patientData.put("closePhysicalContact", closePhysicalContact);
            patientData.put("positiveCovid90Days", positiveCovid90Days);
            patientData.put("selfMonitor", selfMonitor);
            patientData.put("wantCovidTest", wantCovidTest);
            docRef.update("doctorUpcomingAppointments", FieldValue.arrayUnion(patientData)).get();

            sendEmail(patientEmail, "Appointment Booked", "Your appointment with " + doctorName + " has been booked for " + time);
            return true;
        } catch (InterruptedException | ExecutionException | NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void finishAppointment(String patientUid, Date date, String time, String diagnosis, String covidSymptomDetails, String testResults, String insuranceDetails) throws ExecutionException, InterruptedException {
        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            String doctorUid = UserService.getGlobalUid();

            // Get doctor database
            DocumentReference docRef = dbFirestore.collection("Doctors").document(doctorUid);
            DocumentSnapshot doctorSnapshot = docRef.get().get();
            String doctorName = doctorSnapshot.getString("fullName");

            // Get patient database
            DocumentReference patientRef = dbFirestore.collection("Patients").document(patientUid);

            // add booking to patient's appointments history
            Map<String, Object> appointmentData = new HashMap<>();
            appointmentData.put("doctorName", doctorName);
            appointmentData.put("date", date);
            appointmentData.put("time", time);
            appointmentData.put("diagnosis", diagnosis);
            appointmentData.put("covidSymptomsDetails", covidSymptomDetails);
            appointmentData.put("testResults", testResults);
            appointmentData.put("insuranceDetails", insuranceDetails);
            patientRef.update("patientAppointmentHistory", FieldValue.arrayUnion(appointmentData)).get();

            // remove booking from doctor's upcoming appointments
            docRef.update("doctorUpcomingAppointments", FieldValue.arrayRemove(getDoctorAppointmentData(doctorUid, patientUid, date, time))).get();
        } catch (InterruptedException | ExecutionException | NullPointerException e) {
            e.printStackTrace();
            throw new RuntimeException("Error finishing appointment", e);
        }
    }

    private Map<String, Object> getDoctorAppointmentData(String doctorUid, String patientUid, Date date, String time) throws ExecutionException, InterruptedException {
        Firestore checkFirestore = FirestoreClient.getFirestore();
        DocumentReference checkRef = checkFirestore.collection("Doctors").document(doctorUid);
        DocumentSnapshot checkSnapshot = checkRef.get().get();

        if (checkSnapshot.exists()) {
            if (checkSnapshot.contains("doctorUpcomingAppointments")) {
                List<Map<String, Object>> doctorAppointments = (List<Map<String, Object>>) checkSnapshot.get("doctorUpcomingAppointments");
                for (Map<String, Object> appointment : doctorAppointments) {
                    Timestamp appointmentTimestamp = (Timestamp) appointment.get("date");
                    Date appointmentDate = appointmentTimestamp.toDate();
                    if (appointment.get("patientUid").equals(patientUid) && appointment.get("time").equals(time) && appointmentDate.equals(date)) {
                        return appointment;
                    }
                }
            } else {
                throw new RuntimeException("Error getting doctor upcoming appointments");
            }
        } else {
            throw new RuntimeException("doctor snapshot doesn't exist");
        }
        return null;
    }
    
    public void sendEmail(String email, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("careconnect360@gmail.com");
        msg.setTo(email);
        msg.setSubject(subject);
        msg.setText(text);
        javaMailSender.send(msg);
        System.out.println("Mail sent successfully");
    }

    public List<String> checkAvailability(String doctorUid, Date date) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection("Doctors").document(doctorUid);
        DocumentSnapshot docSnapshot = docRef.get().get();
        List<String> availableTimes = new ArrayList<>();
        if (docSnapshot.exists()) {
            if (docSnapshot.contains("doctorUpcomingAppointments")) {
                List<Map<String, Object>> appointments = (List<Map<String, Object>>) docSnapshot.get("doctorUpcomingAppointments");
                for (Map<String, Object> appointment : appointments) {
                    Timestamp appointmentTimestamp = (Timestamp) appointment.get("date");
                    Date appointmentDate = appointmentTimestamp.toDate();
                    if (appointmentDate != null && isSameDate(date, appointmentDate)) {
                        String time = (String) appointment.get("time");
                        if (time != null) {
                            availableTimes.add(time);
                        }
                    }
                }
            }
        }
        return availableTimes;
    }

    private boolean isSameDate(Date date1, Date date2) {
        return date1.getYear() == date2.getYear() && date1.getMonth() == date2.getMonth() && date1.getDate() == date2.getDate();
    }
}
