package com.java.firebase.model.InsuranceProvider;

import com.java.firebase.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InsuranceProvider extends User {
    private String uid;
    private List<Clients> clients;
    private List<InsurancePlan> insurancePlans;

    public InsuranceProvider(User user) {
        super(user.getFirstName(), user.getLastName(), user.getGender(), user.getPhoneNumber(),
                user.getDateOfBirth(), user.getProfilePictureUrl(), user.getStreetAddress(), user.getCountry(),
                user.getState(), user.getCity(), user.getZipCode(), "insuranceProvider", null,
                null, null, user.getCompany(), user.getCompanyLicense(),
                user.getIs2FAEnabled(), user.getPhoneNumberVerified());
        this.clients = new ArrayList<>();
        this.insurancePlans = new ArrayList<>();
    }
    public InsuranceProvider() {
        super();
        this.clients = new ArrayList<>();
        this.insurancePlans = new ArrayList<>();
    }
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public List<Clients> getClients() {
        return clients;
    }
    public void setClients(List<Clients> clients) {
        this.clients = clients;
    }
    public List<InsurancePlan> getInsurancePlans() {
        return insurancePlans;
    }
    public void setInsurancePlans(List<InsurancePlan> insurancePlans) {
        this.insurancePlans = insurancePlans;
    }
}
