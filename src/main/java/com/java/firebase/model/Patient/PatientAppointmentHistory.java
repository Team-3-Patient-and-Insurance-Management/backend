package com.java.firebase.model.Patient;

import java.util.HashMap;
import java.util.Map;

public class PatientAppointmentHistory {
    private String doctorName;
    private String diagnosis;

    public PatientAppointmentHistory(String doctorName, String diagnosis) {
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
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
}
