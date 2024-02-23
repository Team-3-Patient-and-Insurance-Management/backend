package com.java.firebase.model;

import com.google.firebase.auth.UserRecord;

public class Patient extends User {
    public Patient(String phoneNumber, String firstName, String lastName, String address) {
        super(phoneNumber, firstName, lastName, address);
    }
}
