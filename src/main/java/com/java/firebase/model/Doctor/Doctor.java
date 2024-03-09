package com.java.firebase.model.Doctor;

import com.java.firebase.model.User;

import java.util.*;

public class Doctor extends User {

    private String uid;
    private List<DoctorAppointmentHistory> doctorAppointmentHistory;
    private List<DoctorUpcomingAppointments> doctorUpcomingAppointments;

    public Doctor(User user) {
        super(user.getFirstName(), user.getLastName(), user.getGender(), user.getPhoneNumber(),
                user.getDateOfBirth(), user.getStreetAddress(), user.getCountry(), user.getState(), user.getCity(),
                user.getZipCode(), "doctor", user.getDoctorLicense(), user.getSpecialization(), user.getSupportCovid(),
                user.getAppointmentTimes() != null ? user.getAppointmentTimes() : getDefaultAppointmentTimes(),
                null, null, user.getIs2FAEnabled(), user.getPhoneNumberVerified());
        this.doctorAppointmentHistory = new ArrayList<>();
        this.doctorUpcomingAppointments = new ArrayList<>();
    }

    public Doctor() {
        super();
        this.doctorAppointmentHistory = new ArrayList<>();
        this.doctorUpcomingAppointments = new ArrayList<>();
    }

    private static List<String> getDefaultAppointmentTimes() {
        return new ArrayList<>(Arrays.asList("9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00"));
    }

    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<DoctorAppointmentHistory> getDoctorAppointmentHistory() {
        return doctorAppointmentHistory;
    }

    public void setDoctorAppointmentHistory(List<DoctorAppointmentHistory> doctorAppointmentHistory) {
        this.doctorAppointmentHistory = doctorAppointmentHistory;
    }

    public List<DoctorUpcomingAppointments> getDoctorUpcomingAppointments() {
        return doctorUpcomingAppointments;
    }

    public void setDoctorUpcomingAppointments(List<DoctorUpcomingAppointments> doctorUpcomingAppointments) {
        this.doctorUpcomingAppointments = doctorUpcomingAppointments;
    }
}
