package com.java.firebase.model.Doctor;

import com.java.firebase.model.User;

import java.util.*;

public class Doctor extends User {

    private String uid;
    private List<DoctorUpcomingAppointments> doctorUpcomingAppointments;
    private List<DoctorRatings> doctorRatings;
    private String averageRating;
    private String totalRating;
    private String peopleRated;


    public Doctor(User user) {
        super(user.getFirstName(), user.getLastName(), user.getGender(), user.getPhoneNumber(),
                user.getDateOfBirth(), user.getProfilePictureUrl(), user.getStreetAddress(), user.getCountry(),
                user.getState(), user.getCity(), user.getZipCode(), "doctor", user.getDoctorLicense(),
                user.getSpecialization(), user.getSupportCovid(),
                null, null, user.getIs2FAEnabled(), user.getPhoneNumberVerified());
        this.doctorUpcomingAppointments = new ArrayList<>();
        this.doctorRatings = new ArrayList<>();
        this.averageRating = "0";
        this.totalRating = "0";
        this.peopleRated = "0";
    }

    public Doctor() {
        super();
        this.doctorUpcomingAppointments = new ArrayList<>();
        this.doctorRatings = new ArrayList<>();
        this.averageRating = "0";
        this.totalRating = "0";
        this.peopleRated = "0";
    }

    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<DoctorUpcomingAppointments> getDoctorUpcomingAppointments() {
        return doctorUpcomingAppointments;
    }

    public void setDoctorUpcomingAppointments(List<DoctorUpcomingAppointments> doctorUpcomingAppointments) {
        this.doctorUpcomingAppointments = doctorUpcomingAppointments;
    }

    public List<DoctorRatings> getDoctorRatings() {
        return doctorRatings;
    }

    public void setDoctorRatings(List<DoctorRatings> doctorRatings) {
        this.doctorRatings = doctorRatings;
    }
    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public String getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(String totalRating) {
        this.totalRating = totalRating;
    }

    public String getPeopleRated() {
        return peopleRated;
    }

    public void setPeopleRated(String peopleRated) {
        this.peopleRated = peopleRated;
    }
}
