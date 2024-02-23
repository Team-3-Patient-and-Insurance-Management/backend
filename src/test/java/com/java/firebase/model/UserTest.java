package com.java.firebase.model;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private static final String SERVICE_ACCOUNT_KEY_PATH = "src/main/resources/serviceAccountKey.json";
    @BeforeAll
    static void init() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream(SERVICE_ACCOUNT_KEY_PATH);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://patientinsurancemanagement.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
    }
    @Test
    void getAllUsers() throws FirebaseAuthException {
        // Initialize Firebase Admin SDK
        FirebaseAuth auth = FirebaseAuth.getInstance();

        // Retrieve a page of users
        ListUsersPage page = auth.listUsers(null);
        Iterator<ExportedUserRecord> iterator = page.iterateAll().iterator();

        // Iterate over all users and print their details
        while (iterator.hasNext()) {
            UserRecord user = iterator.next();

            System.out.println(user.toString());
            System.out.println("User ID: " + user.getUid());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Phone Number: " + user.getPhoneNumber());
            // Add more user details as needed
        }

    }
    @Test
    void testUser() throws FirebaseAuthException {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String email = "svelet@iu.edu";
        try {
            UserRecord user = auth.getUserByEmail(email);

            FirebaseAuth.getInstance().deleteUser(user.getUid());
        } catch (Exception e) {
            System.out.println();
        }

        UserRecord.CreateRequest userRequest = new UserRecord.CreateRequest()
                .setEmail("svelet@iu.edu")
                .setPassword("password")
                .setPhoneNumber("+18125985490")
                .setDisplayName("Sasha Velet");

        UserRecord sashaRecord = FirebaseAuth.getInstance().createUser(userRequest);

//        User userSasha = new Patient(sashaRecord, "Sasha", "Velet", "1234 Main St");

//        assertEquals("Sasha", userSasha.getFirstName());
//        assertEquals("Velet", userSasha.getLastName());
//        assertEquals("1234 Main St", userSasha.getAddress());
//        assertEquals(sashaRecord.getPhoneNumber(), "+18125985490");
//        assertEquals(sashaRecord, userSasha.getUserRecord());
    }

}