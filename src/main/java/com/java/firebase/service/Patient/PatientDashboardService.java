package com.java.firebase.service.Patient;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.java.firebase.model.Doctor.Doctor;
import com.java.firebase.model.InsuranceProvider.InsuranceProvider;
import com.java.firebase.model.Patient.Patient;
import com.java.firebase.model.Patient.PatientInsuranceProviders;
import com.java.firebase.model.Patient.PatientUpcomingAppointments;
import com.java.firebase.service.UserService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.text.DecimalFormat;

@Service
public class PatientDashboardService {
    public List<PatientUpcomingAppointments> getPatientUpcomingAppointments() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference patientRef = dbFirestore.collection("Patients").document(UserService.getGlobalUid());
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
        DocumentReference patientRef = dbFirestore.collection("Patients").document(UserService.getGlobalUid());
        System.out.println("Patient UID: " + UserService.getGlobalUid());
        DocumentSnapshot patientSnapshot = patientRef.get().get();
        Patient patient = patientSnapshot.toObject(Patient.class);
        if (patient != null) {
            return patient.getPatientInsuranceProviders();
        }
        else {
            return null;
        }
    }

    public List<InsuranceProvider> getAllInsuranceProviders() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference insuranceProvidersCollection = dbFirestore.collection("Insurance Providers");
        Query query = insuranceProvidersCollection;
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<InsuranceProvider> insuranceProviders = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            InsuranceProvider insuranceProvider = document.toObject(InsuranceProvider.class);
            insuranceProviders.add(insuranceProvider);
        }
        return insuranceProviders;
    }

    public void addInsuranceProvider(String insuranceProviderUid, String insurancePlanId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        String patientUid = UserService.getGlobalUid();
        System.out.println("Patient UID: " + UserService.getGlobalUid());

        // Get Patient's Database
        DocumentReference patientRef = dbFirestore.collection("Patients").document(patientUid);
        DocumentSnapshot patientSnapshot = patientRef.get().get();
        String patientName = patientSnapshot.getString("fullName");
        String profilePictureUrl = patientSnapshot.getString("profilePictureUrl");

        // Get Insurance Provider's Database
        DocumentReference insuranceProviderRef = dbFirestore.collection("Insurance Providers").document(insuranceProviderUid);
        DocumentSnapshot insuranceProviderSnapshot = insuranceProviderRef.get().get();
        List<Map<String, Object>> insurancePlans = (List<Map<String, Object>>) insuranceProviderSnapshot.get("insurancePlans");
        String planName = null;
        String description = null;
        Double premium = null;
        Boolean medicalCoverage = null;
        Boolean visionCoverage = null;
        Double deductible = null;
        Boolean dentalCoverage = null;
        for (Map<String, Object> plan : insurancePlans) {
            String planId = (String) plan.get("planId");
            if (planId.equals(insurancePlanId)) {
                planName = (String) plan.get("planName");
                description = (String) plan.get("description");
                premium = (Double) plan.get("premium");
                deductible = (Double) plan.get("deductible");
                medicalCoverage = (Boolean) plan.get("medicalCoverage");
                dentalCoverage = (Boolean) plan.get("dentalCoverage");
                visionCoverage = (Boolean) plan.get("visionCoverage");
                break;
            }
        }

        Map<String, Object> addInsurancePlan = new HashMap<>();
        addInsurancePlan.put("insuranceProviderUid", insuranceProviderUid);
        addInsurancePlan.put("insurancePlanId", insurancePlanId);
        addInsurancePlan.put("planName", planName);
        addInsurancePlan.put("description", description);
        addInsurancePlan.put("premium", premium);
        addInsurancePlan.put("deductible", deductible);
        addInsurancePlan.put("medicalCoverage", medicalCoverage);
        addInsurancePlan.put("dentalCoverage", dentalCoverage);
        addInsurancePlan.put("visionCoverage", visionCoverage);
        patientRef.update("patientInsuranceProviders", FieldValue.arrayUnion(addInsurancePlan)).get();

        // Add patient to insurance provider's database
        Map<String, Object> addPatient = new HashMap<>();
        addPatient.put("profilePictureUrl", profilePictureUrl);
        addPatient.put("patientUid", patientUid);
        addPatient.put("patientName", patientName);
        addPatient.put("planName", planName);
        addPatient.put("description", description);
        insuranceProviderRef.update("clients", FieldValue.arrayUnion(addPatient)).get();
    }

    public void postDoctorReview(String doctorUid, Date date, String time, String title, String review, String stars) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference patientRef = dbFirestore.collection("Patients").document(UserService.getGlobalUid());
        DocumentSnapshot patientSnapshot = patientRef.get().get();
        String patientName = patientSnapshot.getString("fullName");
        Map<String, Object> doctorReview = new HashMap<>();
        doctorReview.put("title", title);
        doctorReview.put("patientName", patientName);
        doctorReview.put("uid", UserService.getGlobalUid());
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
        patientRef.update("patientUpcomingAppointments", FieldValue.arrayRemove(getPatientAppointmentData(doctorUid, UserService.getGlobalUid(), date, time))).get();
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
