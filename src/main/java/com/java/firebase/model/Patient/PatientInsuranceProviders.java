package com.java.firebase.model.Patient;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PatientInsuranceProviders {
    private String insuranceProvider;
    private String policyName;
    private String representative;
    private String description;
    private String price;

    public PatientInsuranceProviders(String insuranceProvider, String policyName, String representative,
                                     String description, String price) {
        this.insuranceProvider = insuranceProvider;
        this.policyName = policyName;
        this.representative = representative;
        this.description = description;
        this.price = price;
    }

    public PatientInsuranceProviders() {
    }

    public String getInsuranceProvider() {
        return insuranceProvider;
    }
    public void setInsuranceProvider(String insuranceProvider) {
        this.insuranceProvider = insuranceProvider;
    }
    public String getPolicyName() {
        return policyName;
    }
    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }
    public String getRepresentative() {
        return representative;
    }
    public void setRepresentative(String representative) {
        this.representative = representative;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
}
