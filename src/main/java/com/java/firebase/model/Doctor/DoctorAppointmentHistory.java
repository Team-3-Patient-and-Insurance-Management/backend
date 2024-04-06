package com.java.firebase.model.Doctor;

import java.util.HashMap;
import java.util.Map;

public class DoctorAppointmentHistory {
    private String patientName;
    private String diagnosis;
    private String covidSymptomsDetails;
    private String testResults;
    private String medicalHistory;
    private String insuranceDetails;

    public DoctorAppointmentHistory(String patientName, String diagnosis, String covidSymptomsDetails,
                                    String testResults, String medicalHistory, String insuranceDetails) {
        this.patientName = patientName;
        this.diagnosis = diagnosis;
        this.covidSymptomsDetails = covidSymptomsDetails;
        this.testResults = testResults;
        this.medicalHistory = medicalHistory;
        this.insuranceDetails = insuranceDetails;
    }

    public DoctorAppointmentHistory() {
    }
    public String getPatientName() {
        return patientName;
    }
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    public String getDiagnosis() {
        return diagnosis;
    }
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    public String getCovidSymptomsDetails() {
        return covidSymptomsDetails;
    }
    public void setCovidSymptomsDetails(String covidSymptomsDetails) {
        this.covidSymptomsDetails = covidSymptomsDetails;
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
