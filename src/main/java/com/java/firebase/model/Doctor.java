package com.java.firebase.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Doctor extends User {

    private List<String> appointmentTimes;
    private List<BookingDetails> bookings;

    public Doctor(User user) {
        super(user.getFirstName(), user.getLastName(), user.getGender(), user.getPhoneNumber(),
                user.getDateOfBirth(), user.getStreetAddress(), user.getCountry(), user.getState(), user.getCity(),
                user.getZipCode(), "doctor", user.getDoctorLicense(), user.getSpecialization(), user.getSupportCovid(),
                user.getAppointmentTimes() != null ? user.getAppointmentTimes() : getDefaultAppointmentTimes(),
                null, null, user.getIs2FAEnabled(), user.getPhoneNumberVerified());
        this.bookings = new ArrayList<>();
    }

    public Doctor() {
        super();
    }

    private static List<String> getDefaultAppointmentTimes() {
        return new ArrayList<>(Arrays.asList("9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00"));
    }

    public List<BookingDetails> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingDetails> bookings) {
        this.bookings = bookings;
    }
}

