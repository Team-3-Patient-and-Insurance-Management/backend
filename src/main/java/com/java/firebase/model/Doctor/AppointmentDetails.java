package com.java.firebase.model.Doctor;

public class AppointmentDetails {
    private String diagnosis;
    private String covidSymptomDetails;
    private String testResults;
    private String medicalHistory;
    private String insuranceDetails;

    public AppointmentDetails() {
    }

    public AppointmentDetails(String diagnosis, String covidSymptomDetails, String testResults, String medicalHistory, String insuranceDetails) {
        this.diagnosis = diagnosis;
        this.covidSymptomDetails = covidSymptomDetails;
        this.testResults = testResults;
        this.medicalHistory = medicalHistory;
        this.insuranceDetails = insuranceDetails;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getCovidSymptomDetails() {
        return covidSymptomDetails;
    }

    public void setCovidSymptomDetails(String covidSymptomDetails) {
        this.covidSymptomDetails = covidSymptomDetails;
    }

    public String getTestResults() {
        return testResults;
    }

    public void setTestResults(String testResults) {
        this.testResults = testResults;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getInsuranceDetails() {
        return insuranceDetails;
    }

    public void setInsuranceDetails(String insuranceDetails) {
        this.insuranceDetails = insuranceDetails;
    }

}
