package com.java.firebase.model.InsuranceProvider;

public class Clients {
    private String uid;
    private String fullName;
    private String planId;
    private String planName;
    private String insuranceCompany;
    private int premium;
    private int deductible;
    private boolean medicalCare;
    private boolean dentalCare;
    private boolean visionCare;

    public Clients() {
    }
    public Clients(String uid, String fullName, String planId, String planName, String insuranceCompany, int premium, int deductible, boolean medicalCare, boolean dentalCare, boolean visionCare) {
        this.uid = uid;
        this.fullName = fullName;
        this.planId = planId;
        this.planName = planName;
        this.insuranceCompany = insuranceCompany;
        this.premium = premium;
        this.deductible = deductible;
        this.medicalCare = medicalCare;
        this.dentalCare = dentalCare;
        this.visionCare = visionCare;
    }
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getPlanId() {
        return planId;
    }
    public void setPlanId(String planId) {
        this.planId = planId;
    }
    public String getPlanName() {
        return planName;
    }
    public void setPlanName(String planName) {
        this.planName = planName;
    }
    public String getInsuranceCompany() {
        return insuranceCompany;
    }
    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }
    public int getPremium() {
        return premium;
    }
    public void setPremium(int premium) {
        this.premium = premium;
    }
    public int getDeductible() {
        return deductible;
    }
    public void setDeductible(int deductible) {
        this.deductible = deductible;
    }
    public boolean isMedicalCare() {
        return medicalCare;
    }
    public void setMedicalCare(boolean medicalCare) {
        this.medicalCare = medicalCare;
    }
    public boolean isDentalCare() {
        return dentalCare;
    }
    public void setDentalCare(boolean dentalCare) {
        this.dentalCare = dentalCare;
    }
    public boolean isVisionCare() {
        return visionCare;
    }
    public void setVisionCare(boolean visionCare) {
        this.visionCare = visionCare;
    }
}