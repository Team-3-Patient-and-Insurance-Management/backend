package com.java.firebase.model;

public class BookingDetails {
    private String time;

    public BookingDetails() {
        // Default constructor required by Firestore
    }

    public BookingDetails(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
