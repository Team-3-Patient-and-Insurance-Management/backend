package com.java.firebase.service.InsuranceProvider;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.java.firebase.model.InsuranceProvider.InsurancePlan;
import com.java.firebase.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class InsurancePlanService {

    public List<InsurancePlan> getAllInsurancePlans() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        List<InsurancePlan> insurancePlans = null;

        // Assuming insurance plans are stored in a collection named "InsurancePlans"
        DocumentReference insurancePlansRef = dbFirestore.collection("InsurancePlans").document(UserService.getInstance().globalUid);
        DocumentSnapshot insurancePlansSnapshot = insurancePlansRef.get().get();
        InsurancePlan insurancePlan = insurancePlansSnapshot.toObject(InsurancePlan.class);
        if (insurancePlan != null) {
            return insurancePlan.getAllInsurancePlans();
        } else {
            return null;
        }


        public void chooseInsurancePlan (String userId, String planId){
            // Here you can implement the logic to associate the chosen plan with the user
            // For example, update the user's profile in the database with the selected plan
            // This logic will depend on your application's data model and requirements
            // You might need to inject UserRepository and implement methods to update user profiles

        }
    }
}
