package com.java.firebase.model;

import com.google.firebase.auth.UserRecord;

public class InsuranceProvider extends User {
    public InsuranceProvider(UserRecord userRecord, String phoneNumber, String firstName, String lastName, String address) {
        super(userRecord, phoneNumber, firstName, lastName, address);
    }
}
