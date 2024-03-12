package com.java.firebase.model.Doctor;

import java.util.HashMap;
import java.util.Map;

public class DoctorAppointmentHistory {
    private String patientName;
    private String diagnosis;

    public DoctorAppointmentHistory(String patientName, String diagnosis) {
        this.patientName = patientName;
        this.diagnosis = diagnosis;
    }

    public DoctorAppointmentHistory() {
        // Default constructor required by Firebase Firestore for deserialization
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
}
