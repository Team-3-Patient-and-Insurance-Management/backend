package com.java.firebase.model;

import java.util.Date;

public class InsuranceProvider extends User {

    public InsuranceProvider(User user) {
        super(user.getFirstName(), user.getLastName(), user.getGender(), user.getPhoneNumber(),
                user.getDateOfBirth(), user.getStreetAddress(), user.getCountry(), user.getState(), user.getCity(),
                user.getZipCode(), "InsuranceProvider", null, null, null,
                null, user.getCompany(), user.getCompanyLicense(), user.getIs2FAEnabled(),
                user.getPhoneNumberVerified());
    }

    public InsuranceProvider() {
        super();
    }
}
