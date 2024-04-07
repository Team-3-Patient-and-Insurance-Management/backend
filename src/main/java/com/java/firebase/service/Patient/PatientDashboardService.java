package com.java.firebase.service.Patient;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.java.firebase.model.Patient.Patient;
import com.java.firebase.model.Patient.PatientInsuranceProviders;
import com.java.firebase.model.Patient.PatientUpcomingAppointments;
import com.java.firebase.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.text.DecimalFormat;

@Service
public class PatientDashboardService {
    public List<PatientUpcomingAppointments> getPatientUpcomingAppointments() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference patientRef = dbFirestore.collection("Patients").document(UserService.getInstance().globalUid);
        DocumentSnapshot patientSnapshot = patientRef.get().get();
        Patient patient = patientSnapshot.toObject(Patient.class);
        if (patient != null) {
            return patient.getPatientUpcomingAppointments();
        }
        else {
            return null;
        }
    }

    public List<PatientInsuranceProviders> getPatientInsuranceProviders() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference patientRef = dbFirestore.collection("Patients").document(UserService.getInstance().globalUid);
        DocumentSnapshot patientSnapshot = patientRef.get().get();
        Patient patient = patientSnapshot.toObject(Patient.class);
        if (patient != null) {
            return patient.getPatientInsuranceProviders();
        }
        else {
            return null;
        }
    }

    public void postDoctorReview(String doctorUid, Date date, String time, String title, String review, String stars) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference patientRef = dbFirestore.collection("Patients").document(UserService.getInstance().globalUid);
        DocumentSnapshot patientSnapshot = patientRef.get().get();
        String patientName = patientSnapshot.getString("fullName");
        Map<String, Object> doctorReview = new HashMap<>();
        doctorReview.put("title", title);
        doctorReview.put("patientName", patientName);
        doctorReview.put("review", review);
        doctorReview.put("stars", stars);

        // Get doctor database
        DocumentReference docRef = dbFirestore.collection("Doctors").document(doctorUid);

        // Update doctor ratings
        docRef.update("doctorRatings", FieldValue.arrayUnion(doctorReview)).get();

        // Update doctor average ratings
        DocumentSnapshot doctorSnapshot = docRef.get().get();
        String totalRating = doctorSnapshot.getString("totalRating");
        String peopleRated = doctorSnapshot.getString("peopleRated");
        int newTotalRating = Integer.parseInt(totalRating) + Integer.parseInt(stars);
        int newPeopleRated = Integer.parseInt(peopleRated) + 1;
        double newAverageRating = (double) newTotalRating / newPeopleRated;
        DecimalFormat df = new DecimalFormat("#.#");
        String formattedAverageRating = df.format(newAverageRating);
        docRef.update("averageRating", String.valueOf(formattedAverageRating)).get();

        // remove booking from doctor's upcoming appointments
        patientRef.update("patientUpcomingAppointments", FieldValue.arrayRemove(getPatientAppointmentData(doctorUid, UserService.getInstance().globalUid, date, time))).get();
    }

    private Map<String, Object> getPatientAppointmentData(String doctorUid, String patientUid, Date date, String time) throws ExecutionException, InterruptedException {
        Firestore checkFirestore = FirestoreClient.getFirestore();
        DocumentReference checkRef = checkFirestore.collection("Patients").document(patientUid);
        DocumentSnapshot checkSnapshot = checkRef.get().get();

        if (checkSnapshot.exists()) {
            if (checkSnapshot.contains("patientUpcomingAppointments")) {
                List<Map<String, Object>> patientAppointments = (List<Map<String, Object>>) checkSnapshot.get("patientUpcomingAppointments");
                for (Map<String, Object> appointment : patientAppointments) {
                    Timestamp appointmentTimestamp = (Timestamp) appointment.get("date");
                    Date appointmentDate = appointmentTimestamp.toDate();
                    if (appointment.get("doctorUid").equals(doctorUid) && appointment.get("time").equals(time) && appointmentDate.equals(date)) {
                        return appointment;
                    }
                }
            } else {
                throw new RuntimeException("Error getting patient upcoming appointments");
            }
        } else {
            throw new RuntimeException("patient snapshot doesn't exist");
        }
        return null;
    }
}
