package com.java.firebase.model.Doctor;

public class DoctorReview {

    private String patientName;
    private String description;
    private int rating;
    private String title;

    public DoctorReview(String patientName, String description, int rating, String title) {
        this.patientName = patientName;
        this.description = description;
        this.rating = rating;
        this.title = title;
    }

    public DoctorReview() {
    }


    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
