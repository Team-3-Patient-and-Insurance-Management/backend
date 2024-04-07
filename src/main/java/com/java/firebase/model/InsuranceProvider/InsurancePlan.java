package com.java.firebase.model.InsuranceProvider;

public class InsurancePlan {
    private String planId;
    private String planName;
    private String description;
    private double premium;
    private double deductible;
    private boolean medicalCoverage;
    private boolean dentalCoverage;
    private boolean visionCoverage;
    public InsurancePlan() {
    }

    public InsurancePlan(String planId, String planName, String description, double premium, double deductible, boolean medicalCoverage, boolean dentalCoverage, boolean visionCoverage) {
        this.planId = planId;
        this.planName = planName;
        this.description = description;
        this.premium = premium;
        this.deductible = deductible;
        this.medicalCoverage = medicalCoverage;
        this.dentalCoverage = dentalCoverage;
        this.visionCoverage = visionCoverage;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

}