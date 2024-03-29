package com.java.firebase.model.InsuranceProvider;

import com.java.firebase.model.User;

import java.util.Date;

public class InsuranceProvider extends User {
    private String uid;

    public InsuranceProvider(User user) {
        super(user.getFirstName(), user.getLastName(), user.getGender(), user.getPhoneNumber(),
                user.getDateOfBirth(), user.getProfilePictureUrl(), user.getStreetAddress(), user.getCountry(),
                user.getState(), user.getCity(), user.getZipCode(), "insuranceProvider", null,
                null, null, user.getCompany(), user.getCompanyLicense(),
                user.getIs2FAEnabled(), user.getPhoneNumberVerified());
    }
    public InsuranceProvider() {
        super();
    }
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
}
