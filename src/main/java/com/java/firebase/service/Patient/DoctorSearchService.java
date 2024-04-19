package com.java.firebase.service.Patient;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.java.firebase.model.Doctor.Doctor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class DoctorSearchService {

    public List<Doctor> searchDoctors(String partialName, String specialization, boolean covidSupport) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference doctorsCollection = dbFirestore.collection("Doctors");
        Query query = doctorsCollection;

        if (partialName != null && !partialName.isEmpty()) {
            String partialNameLower = partialName.toLowerCase();
            String partialNameUpper = partialNameLower + "\uf8ff";
            query = query.whereGreaterThanOrEqualTo("fullName", partialNameLower)
                    .whereLessThan("fullName", partialNameUpper);
        }

        if (specialization != null && !specialization.isEmpty()) {
            String specializationLower = specialization.toLowerCase();
            String specializationUpper = specializationLower + "\uf8ff";
            query = query.whereGreaterThanOrEqualTo("specialization", specializationLower)
                    .whereLessThan("specialization", specializationUpper);
        }

        if (covidSupport) {
            query = query.whereEqualTo("supportCovid", true);
        }

        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<Doctor> doctors = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            Doctor doctor = document.toObject(Doctor.class);
            doctors.add(doctor);
        }
        return doctors;
    }

    public Doctor getDoctorDetails(String doctorUid) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection("Doctors").document(doctorUid);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        Doctor doctor = null;
        if (document.exists()) {
            doctor = document.toObject(Doctor.class);
        }
        return doctor;
    }
}
