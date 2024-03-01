package com.java.firebase.model;

import java.util.Date;

public class Patient extends User {

    public Patient(User user) {
        super(user.getFirstName(), user.getLastName(), user.getGender(), user.getPhoneNumber(),
                user.getDateOfBirth(), user.getStreetAddress(), user.getCountry(), user.getState(), user.getCity(),
                user.getZipCode(), "Patient", null, null, null, null,
                null, null, user.getIs2FAEnabled(), user.getPhoneNumberVerified());
    }
    public Patient() {
        super();
    }
}
