package com.java.firebase.service.Doctor;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.java.firebase.model.Doctor.Doctor;
import com.java.firebase.model.Doctor.DoctorAppointmentHistory;
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
        DocumentReference doctorRef = dbFirestore.collection("Doctors").document(UserService.getInstance().globalUid);
        DocumentSnapshot doctorSnapshot = doctorRef.get().get();
        Doctor doctor = doctorSnapshot.toObject(Doctor.class);
        if (doctor != null) {
            return doctor.getDoctorUpcomingAppointments();
        }
        else {
            return null;
        }
    }

    public List<DoctorAppointmentHistory> getDoctorAppointmentHistory() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference doctorRef = dbFirestore.collection("Doctors").document(UserService.getInstance().globalUid);
        DocumentSnapshot doctorSnapshot = doctorRef.get().get();
        Doctor doctor = doctorSnapshot.toObject(Doctor.class);
        if (doctor != null) {
            return doctor.getDoctorAppointmentHistory();
        }
        else {
            return null;
        }
    }

    public List<PatientAppointmentHistory> getPatientAppointmentHistory(String patientUid) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference patientRef = dbFirestore.collection("Patients").document(UserService.getInstance().globalUid);
        DocumentSnapshot patientSnapshot = patientRef.get().get();
        Patient patient = patientSnapshot.toObject(Patient.class);
        if (patient != null) {
            return patient.getPatientAppointmentHistory();
        }
        else {
            return null;
        }
    }
}
