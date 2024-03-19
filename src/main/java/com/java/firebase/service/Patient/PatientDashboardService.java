package com.java.firebase.service.Patient;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.java.firebase.model.Patient.Patient;
import com.java.firebase.model.Patient.PatientInsuranceProviders;
import com.java.firebase.model.Patient.PatientUpcomingAppointments;
import com.java.firebase.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

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
}
