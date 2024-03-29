package com.java.firebase.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import com.java.firebase.model.Doctor.Doctor;
import com.java.firebase.model.InsuranceProvider.InsuranceProvider;
import com.java.firebase.model.Patient.Patient;
import com.java.firebase.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {
    private static UserService instance;
    public String globalToken;
    public String globalUid;
    public String globalEmail;
    private UserService() {
    }
    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public String signUpUser(String email, String password, User user) throws ExecutionException, InterruptedException {
        try {
            UserRecord.CreateRequest userRequest = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setPassword(password)
                    .setPhoneNumber(user.getPhoneNumber())
                    .setEmailVerified(false);
            UserRecord userRecord = FirebaseAuth.getInstance().createUser(userRequest);
            /**
             * 1. Send verification code to user to verify phone number
             * 2. After phone number is verified, add user to database
             */
            saveUserToFirestore(userRecord, user);
            return "User (" + user.getRole() + ") signed up successfully with uid: " + userRecord.getUid();
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return "Error signing up user: " + e.getMessage();
        }
    }

    private void saveUserToFirestore(UserRecord userRecord, User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Map<String, Object> userData = new HashMap<>();
        userData.put("role", user.getRole());
        ApiFuture<WriteResult> userResult = dbFirestore.collection("Users").document(userRecord.getUid()).set(userData);
        userResult.get();
        String roleCollection = "";
        if (user.getRole().equals("doctor")) {
            roleCollection = "Doctors";
            Doctor doctor = new Doctor(user);
            doctor.setUid(userRecord.getUid());
            ApiFuture<WriteResult> doctorResult = dbFirestore.collection(roleCollection).document(userRecord.getUid()).set(doctor);
            doctorResult.get();
        } else if (user.getRole().equals("patient")) {
            roleCollection = "Patients";
            Patient patient = new Patient(user);
            patient.setUid(userRecord.getUid());
            ApiFuture<WriteResult> patientResult = dbFirestore.collection(roleCollection).document(userRecord.getUid()).set(patient);
            patientResult.get();
        } else if (user.getRole().equals("insuranceProvider")) {
            roleCollection = "Insurance Providers";
            InsuranceProvider insuranceProvider = new InsuranceProvider(user);
            insuranceProvider.setUid(userRecord.getUid());
            ApiFuture<WriteResult> insuranceProviderResult = dbFirestore.collection(roleCollection).document(userRecord.getUid()).set(insuranceProvider);
            insuranceProviderResult.get();
        }
    }

    public String signInUser(@RequestBody String idToken) {
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            this.globalToken = idToken;
            this.globalUid = decodedToken.getUid();
            this.globalEmail = decodedToken.getEmail();
            Firestore dbFirestore = FirestoreClient.getFirestore();
            DocumentReference userRef = dbFirestore.collection("Users").document(globalUid);
            DocumentSnapshot userSnapshot = userRef.get().get();
            if (userSnapshot.exists()) {
                String role = userSnapshot.getString("role");
                if (role != null) {
                    return role;
                } else {
                    return "User does not have a role";
                }
            } else {
                return "User does not exist";
            }
            /**
             * 1. Check if user exists in database via their primary key (uid)
             * 2. If user exists, check if 2FA is enabled
             * 3. If 2FA is enabled and phone # is verified, send 2FA code
             *
             * 4. If 2FA is not enabled, sign in user
             */

        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return "Error signing in user: " + e.getMessage();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getGlobalUid() {
        return globalUid;
    }

    public String getGlobalEmail() {
        return globalEmail;
    }

}
