package com.java.firebase.model.Patient;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PatientInsuranceProviders {
    private Double deductible;
    private Boolean dentalCoverage;
    private String insurancePlanId;
    private String description;
    private String insuranceProviderUid;
    private Boolean medicalCoverage;
    private String planName;
    private Double premium;
    private Boolean visionCoverage;

    public Double getDeductible() {
        return deductible;
    }

    public void setDeductible(Double deductible) {
        this.deductible = deductible;
    }

    public Boolean getDentalCoverage() {
        return dentalCoverage;
    }

    public void setDentalCoverage(Boolean dentalCoverage) {
        this.dentalCoverage = dentalCoverage;
    }

    public String getInsurancePlanId() {
        return insurancePlanId;
    }

    public void setInsurancePlanId(String insurancePlanId) {
        this.insurancePlanId = insurancePlanId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInsuranceProviderUid() {
        return insuranceProviderUid;
    }

    public void setInsuranceProviderUid(String insuranceProviderUid) {
        this.insuranceProviderUid = insuranceProviderUid;
    }

    public Boolean getMedicalCoverage() {
        return medicalCoverage;
    }

    public void setMedicalCoverage(Boolean medicalCoverage) {
        this.medicalCoverage = medicalCoverage;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Double getPremium() {
        return premium;
    }

    public void setPremium(Double premium) {
        this.premium = premium;
    }

    public Boolean getVisionCoverage() {
        return visionCoverage;
    }

    public void setVisionCoverage(Boolean visionCoverage) {
        this.visionCoverage = visionCoverage;
    }

    public PatientInsuranceProviders(Double deductible, Boolean dentalCoverage, String insurancePlanId, String description, String insuranceProviderUid, Boolean medicalCoverage, String planName, Double premium, Boolean visionCoverage) {
        this.deductible = deductible;
        this.dentalCoverage = dentalCoverage;
        this.insurancePlanId = insurancePlanId;
        this.description = description;
        this.insuranceProviderUid = insuranceProviderUid;
        this.medicalCoverage = medicalCoverage;
        this.planName = planName;
        this.premium = premium;
        this.visionCoverage = visionCoverage;
    }

    public PatientInsuranceProviders() {
    }

}
