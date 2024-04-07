package com.java.firebase.model.InsuranceProvider;

public class Clients {
    private String patientUid;
    private String patientName;
    private String planName;
    private String description;

    public Clients() {
    }
    public Clients(String patientUid, String patientName, String planName, String description) {
        this.patientUid = patientUid;
        this.patientName = patientName;
        this.planName = planName;
        this.description = description;
    }
    public String getPatientUid() {
        return patientUid;
    }
    public void setPatientUid(String patientUid) {
        this.patientUid = patientUid;
    }
    public String getPatientName() {
        return patientName;
    }
    public void setPatientName(String patientName) {
        this.patientName = patientName;
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
}