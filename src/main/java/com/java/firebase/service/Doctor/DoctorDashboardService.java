package com.java.firebase.service.Doctor;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.java.firebase.model.Doctor.Doctor;
import com.java.firebase.model.Doctor.DoctorRatings;
import com.java.firebase.model.Doctor.DoctorUpcomingAppointments;
import com.java.firebase.model.Patient.Patient;
import com.java.firebase.model.Patient.PatientAppointmentHistory;
import com.java.firebase.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class DoctorDashboardService {
    public List<DoctorUpcomingAppointments> getDoctorUpcomingAppointments() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference doctorRef = dbFirestore.collection("Doctors").document(UserService.getGlobalUid());
        System.out.println("Doctor UID: " + UserService.getGlobalUid());
        System.out.println("its here in upcoming appointments");
        DocumentSnapshot doctorSnapshot = doctorRef.get().get();
        Doctor doctor = doctorSnapshot.toObject(Doctor.class);
        if (doctor != null) {
            return doctor.getDoctorUpcomingAppointments();
        }
        else {
            return null;
        }
    }

    public Patient getPatientDetails(String patientUid) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference patientRef = dbFirestore.collection("Patients").document(UserService.getGlobalUid());
        DocumentSnapshot patientSnapshot = patientRef.get().get();
        Patient patient = patientSnapshot.toObject(Patient.class);
        if (patient != null) {
            return patient;
        }
        else {
            return null;
        }
    }

    public List<DoctorRatings> getDoctorRatings() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference doctorRef = dbFirestore.collection("Doctors").document(UserService.getGlobalUid());
        System.out.println("Doctor UID: " + UserService.getGlobalUid());
        System.out.println("its here in doctor ratings");

        DocumentSnapshot doctorSnapshot = doctorRef.get().get();
        Doctor doctor = doctorSnapshot.toObject(Doctor.class);
        if (doctor != null) {
            return doctor.getDoctorRatings();
        }
        else {
            return null;
        }
    }
}
