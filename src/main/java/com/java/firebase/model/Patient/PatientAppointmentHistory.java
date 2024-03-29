package com.java.firebase.model.Patient;

import java.util.HashMap;
import java.util.Map;

public class PatientAppointmentHistory {
    private String doctorName;
    private String diagnosis;
    private String covidSymptomsDetails;
    private String testResults;
    private String medicalHistory;
    private String insuranceDetails;

    public PatientAppointmentHistory(String doctorName, String diagnosis, String covidSymptomsDetails,
                                     String testResults, String medicalHistory, String insuranceDetails) {
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
        this.covidSymptomsDetails = covidSymptomsDetails;
        this.testResults = testResults;
        this.medicalHistory = medicalHistory;
        this.insuranceDetails = insuranceDetails;
    }
    public PatientAppointmentHistory() {
    }

    public String getDoctorName() {
        return doctorName;
    }
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
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
