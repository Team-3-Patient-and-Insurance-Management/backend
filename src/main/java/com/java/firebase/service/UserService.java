package com.java.firebase.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import com.java.firebase.controllers.Twilio2FAController;
import com.java.firebase.model.Doctor.Doctor;
import com.java.firebase.model.InsuranceProvider.InsuranceProvider;
import com.java.firebase.model.Patient.Patient;
import com.java.firebase.model.User;
import org.springframework.stereotype.Service;

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
        String roleCollection = getRoleCollection(user.getRole());
        if (roleCollection.equals("Doctors")) {
            Doctor doctor = new Doctor(user);
            doctor.setUid(userRecord.getUid());
            ApiFuture<WriteResult> doctorResult = dbFirestore.collection(roleCollection).document(userRecord.getUid()).set(doctor);
            doctorResult.get();
        } else if (roleCollection.equals("Patients")) {
            Patient patient = new Patient(user);
            patient.setUid(userRecord.getUid());
            ApiFuture<WriteResult> patientResult = dbFirestore.collection(roleCollection).document(userRecord.getUid()).set(patient);
            patientResult.get();
        } else if (roleCollection.equals("Insurance Providers")) {
            InsuranceProvider insuranceProvider = new InsuranceProvider(user);
            insuranceProvider.setUid(userRecord.getUid());
            ApiFuture<WriteResult> insuranceProviderResult = dbFirestore.collection(roleCollection).document(userRecord.getUid()).set(insuranceProvider);
            insuranceProviderResult.get();
        }

    }

    public String signInUser(String idToken) {
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            this.globalToken = idToken;
            this.globalUid = decodedToken.getUid();
            this.globalEmail = decodedToken.getEmail();

            User user =  getUser(this.globalUid);
            return Twilio2FAController.generateOTP(user.getPhoneNumber());
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return "Error signing in user: " + e.getMessage();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String verifyPin(String pinCode) {
        try {
            User user = getUser(this.globalUid);
            String result = Twilio2FAController.verifyOTP(user.getPhoneNumber(), pinCode);
            if (result.equals("approved")) {
                return user.getRole();
            } else {
                // result is "pending", meaning incorrect pincode
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User getUser(String uid) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference userDocRef = dbFirestore.collection("Users").document(uid);
        ApiFuture<DocumentSnapshot> userFuture = userDocRef.get();
        System.out.println("getting user");
        DocumentSnapshot userDocument = userFuture.get();
        if (userDocument.exists()) {
            Map<String, Object> userData = userDocument.getData();
            String role = (String) userData.get("role");
            System.out.println("exists " + role);

            String roleCollection = getRoleCollection(role);
            if (!roleCollection.isEmpty()) {
                DocumentReference roleDocRef = dbFirestore.collection(roleCollection).document(uid);
                ApiFuture<DocumentSnapshot> roleFuture = roleDocRef.get();
                System.out.println("getting role");

                DocumentSnapshot roleDocument = roleFuture.get();
                if (roleDocument.exists()) {
                    switch (role) {
                        case "doctor":
                            System.out.println("in doctor");
                            Doctor doctor = roleDocument.toObject(Doctor.class);
                            if (doctor != null) {
                                doctor.setUid(uid);
                            }
                            return doctor;
                        case "patient":
                            Patient patient = roleDocument.toObject(Patient.class);
                            if (patient != null) {
                                patient.setUid(uid);
                            }
                            return patient;
                        case "insuranceProvider":
                            InsuranceProvider insuranceProvider = roleDocument.toObject(InsuranceProvider.class);
                            if (insuranceProvider != null) {
                                insuranceProvider.setUid(uid);
                            }
                            return insuranceProvider;
                        default:
                            return null;
                    }
                }
            }
        }
        return null;
    }

    private String getRoleCollection(String role) {
        String roleCollection = "";

        switch (role) {
            case "doctor":
                roleCollection = "Doctors";
                break;
            case "patient":
                roleCollection = "Patients";
                break;
            case "insuranceProvider":
                roleCollection = "Insurance Providers";
                break;
            default:
                break;
        }

        return roleCollection;
    }


    public String getGlobalUid() {
        return globalUid;
    }

    public String getGlobalEmail() {
        return globalEmail;
    }

}
