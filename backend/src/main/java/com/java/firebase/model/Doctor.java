package com.java.firebase.model;

import com.google.firebase.auth.UserRecord;

public class Doctor extends User {
    public Doctor(UserRecord userRecord, String phoneNumber, String firstName, String lastName, String address) {
        super(userRecord, phoneNumber, firstName, lastName, address);
    }
}
