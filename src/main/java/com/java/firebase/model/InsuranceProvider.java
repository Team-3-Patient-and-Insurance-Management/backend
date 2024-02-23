package com.java.firebase.model;

import com.google.firebase.auth.UserRecord;

public class InsuranceProvider extends User {
    public InsuranceProvider(String phoneNumber, String firstName, String lastName, String address) {
        super(phoneNumber, firstName, lastName, address);
    }
}
