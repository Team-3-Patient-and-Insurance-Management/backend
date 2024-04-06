package com.java.firebase.model.InsuranceProvider;


import java.util.List;

public class InsurancePlan {
    private String planId;
    private String name;
    private String insuranceCompany;
    private double premium;
    private double deductible;
    private boolean medicalCoverage;
    private boolean dentalCoverage;
    private boolean visionCoverage;
    private List<InsurancePlan> allInsurancePlans;
    // Add more fields as needed

    // Constructors, getters, and setters
    public InsurancePlan() {
    }

    public InsurancePlan(String planId, String name, String insuranceCompany, double premium, double deductible, boolean medicalCoverage, boolean dentalCoverage, boolean visionCoverage) {
        this.planId = planId;
        this.name = name;
        this.insuranceCompany = insuranceCompany;
        this.premium = premium;
        this.deductible = deductible;
        this.medicalCoverage = medicalCoverage;
        this.dentalCoverage = dentalCoverage;
        this.visionCoverage = visionCoverage;
        this.allInsurancePlans = allInsurancePlans;

    }

    // Getters and setters for the fields
    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPremium() {
        return premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }

    public double getDeductible() {
        return deductible;
    }

    public void setDeductible(double deductible) {
        this.deductible = deductible;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public boolean isMedicalCoverage() {
        return medicalCoverage;
    }

    public void setMedicalCoverage(boolean medicalCoverage) {
        this.medicalCoverage = medicalCoverage;
    }

    public boolean isDentalCoverage() {
        return dentalCoverage;
    }

    public void setDentalCoverage(boolean dentalCoverage) {
        this.dentalCoverage = dentalCoverage;
    }

    public boolean isVisionCoverage() {
        return visionCoverage;
    }

    public void setVisionCoverage(boolean visionCoverage) {
        this.visionCoverage = visionCoverage;
    }

    public List<InsurancePlan> getAllInsurancePlans(){
        return allInsurancePlans;
    }

}
