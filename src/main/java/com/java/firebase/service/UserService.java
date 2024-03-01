package com.java.firebase.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import com.java.firebase.model.Doctor;
import com.java.firebase.model.InsuranceProvider;
import com.java.firebase.model.Patient;
import com.java.firebase.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.concurrent.ExecutionException;

@Service
public class UserService {
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
            switch (user.getRole()) {
                case "doctor":
                    Doctor doctor = new Doctor(user);
                    saveUserToFirestore(userRecord, doctor);
                    break;
                case "patient":
                    Patient patient = new Patient(user);
                    saveUserToFirestore(userRecord, patient);
                    break;
                case "insuranceProvider":
                    InsuranceProvider insuranceProvider = new InsuranceProvider(user);
                    saveUserToFirestore(userRecord, insuranceProvider);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid role: " + user.getRole());
            }
            return "User (" + user.getRole() + ") signed up successfully with uid: " + userRecord.getUid();
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return "Error signing up user: " + e.getMessage();
        }
    }

    private void saveUserToFirestore(UserRecord userRecord, User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> userWriteResult = dbFirestore.collection("Users").document(userRecord.getUid()).set(user);
        userWriteResult.get();
        String roleCollection = "";
        if (user.getRole().equals("doctor")) {
            roleCollection = "Doctors";
        } else if (user.getRole().equals("patient")) {
            roleCollection = "Patients";
        } else if (user.getRole().equals("insuranceProvider")) {
            roleCollection = "Insurance Providers";
        }
        if (!roleCollection.isEmpty()) {
            ApiFuture<WriteResult> roleWriteResult = dbFirestore.collection(roleCollection).document(userRecord.getUid()).set(user);
            roleWriteResult.get();
        }
    }

    public String signInUser(@RequestBody String idToken) {
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String uid = decodedToken.getUid();

            /**
             * 1. Check if user exists in database via their primary key (uid)
             * 2. If user exists, check if 2FA is enabled
             * 3. If 2FA is enabled and phone # is verified, send 2FA code
             *
             * 4. If 2FA is not enabled, sign in user
             */

            return "User signed in successfully";
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return "Error signing in user: " + e.getMessage();
        }
    }
}
