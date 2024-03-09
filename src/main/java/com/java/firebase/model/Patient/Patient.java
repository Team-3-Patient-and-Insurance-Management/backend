package com.java.firebase.model.Patient;

import com.java.firebase.model.User;

import java.util.ArrayList;
import java.util.List;

public class Patient extends User {
    private String uid;
    private List<PatientAppointmentHistory> patientAppointmentHistory;
    private List<PatientUpcomingAppointments> patientUpcomingAppointments;
    private List<PatientInsuranceProviders> patientInsuranceProviders;

    public Patient(User user) {
        super(user.getFirstName(), user.getLastName(), user.getGender(), user.getPhoneNumber(),
                user.getDateOfBirth(), user.getStreetAddress(), user.getCountry(), user.getState(), user.getCity(),
                user.getZipCode(), "patient", null, null, null, null,
                null, null, user.getIs2FAEnabled(), user.getPhoneNumberVerified());
        this.patientAppointmentHistory = new ArrayList<>();
        this.patientUpcomingAppointments = new ArrayList<>();
        this.patientInsuranceProviders = new ArrayList<>();
    }

    public Patient() {
        super();
        this.patientAppointmentHistory = new ArrayList<>();
        this.patientUpcomingAppointments = new ArrayList<>();
        this.patientInsuranceProviders = new ArrayList<>();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<PatientAppointmentHistory> getPatientAppointmentHistory() {
        return patientAppointmentHistory;
    }

    public void setPatientAppointmentHistory(List<PatientAppointmentHistory> patientAppointmentHistory) {
        this.patientAppointmentHistory = patientAppointmentHistory;
    }

    public List<PatientUpcomingAppointments> getPatientUpcomingAppointments() {
        return patientUpcomingAppointments;
    }

    public void setPatientUpcomingAppointments(List<PatientUpcomingAppointments> upcomingAppointments) {
        this.patientUpcomingAppointments = upcomingAppointments;
    }

    public List<PatientInsuranceProviders> getPatientInsuranceProviders() {
        return patientInsuranceProviders;
    }

    public void setPatientInsuranceProviders(List<PatientInsuranceProviders> patientInsuranceProviders) {
        this.patientInsuranceProviders = patientInsuranceProviders;
    }
}
