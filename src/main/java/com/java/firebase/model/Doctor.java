package com.java.firebase.model;

import com.google.firebase.auth.UserRecord;

public class Doctor extends User {
    public Doctor(String phoneNumber, String firstName, String lastName, String address) {
        super(phoneNumber, firstName, lastName, address);
    }
}
