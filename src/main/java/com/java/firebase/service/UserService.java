package com.java.firebase.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
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
            // Remove sensitive fields from User object before saving to Firestore
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("Users").document(userRecord.getUid()).set(user);
            return collectionsApiFuture.get().getUpdateTime().toString();
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return "Error signing up user: " + e.getMessage();
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

    public User getUser(String uid) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection("Users").document(uid);
        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return document.toObject(User.class);
        } else {
            return null; // User not found
        }
    }

    public void updateUser(String uid, User newUser) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> future = dbFirestore.collection("Users").document(uid).set(newUser, SetOptions.merge());
    }
}
