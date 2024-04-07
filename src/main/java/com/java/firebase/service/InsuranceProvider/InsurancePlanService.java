package com.java.firebase.service.InsuranceProvider;

import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.java.firebase.model.InsuranceProvider.InsurancePlan;
import com.java.firebase.model.InsuranceProvider.Clients;
import com.java.firebase.model.InsuranceProvider.InsuranceProvider;
import com.java.firebase.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class InsurancePlanService {

    public List<InsurancePlan> getAllInsurancePlans() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference insurancePlansRef = dbFirestore.collection("Insurance Providers").document(UserService.getInstance().globalUid);
        DocumentSnapshot insurancePlansSnapshot = insurancePlansRef.get().get();
        InsuranceProvider insuranceProvider = insurancePlansSnapshot.toObject(InsuranceProvider.class);
        if (insuranceProvider != null) {
            return insuranceProvider.getInsurancePlans();
        } else {
            return null;
        }
    }

    public List<Clients> getAllClients() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference insuranceRef = dbFirestore.collection("Insurance Providers").document(UserService.getInstance().globalUid);
        DocumentSnapshot insuranceSnapshot = insuranceRef.get().get();
        InsuranceProvider insuranceProvider = insuranceSnapshot.toObject(InsuranceProvider.class);
        if (insuranceProvider != null) {
            return insuranceProvider.getClients();
        }
        else {
            return null;
        }
    }

    public void addInsurancePlan(String planName, String description, double premium, double deductible, boolean medicalCoverage, boolean dentalCoverage, boolean visionCoverage) throws ExecutionException, InterruptedException {
        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            String insuranceProviderId = UserService.getInstance().globalUid;
            DocumentReference insuranceRef = dbFirestore.collection("Insurance Providers").document(insuranceProviderId);
            String planId = dbFirestore.collection("Insurance Providers").document().getId();
            Map<String, Object> insuranceData = new HashMap<>();
            insuranceData.put("planId", planId);
            insuranceData.put("planName", planName);
            insuranceData.put("description", description);
            insuranceData.put("premium", premium);
            insuranceData.put("deductible", deductible);
            insuranceData.put("medicalCoverage", medicalCoverage);
            insuranceData.put("dentalCoverage", dentalCoverage);
            insuranceData.put("visionCoverage", visionCoverage);
            insuranceRef.update("insurancePlans", FieldValue.arrayUnion(insuranceData)).get();
        } catch (InterruptedException | ExecutionException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}