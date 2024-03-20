package com.java.firebase.model.Doctor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DoctorUpcomingAppointments {
    private String patientName;
    private String patientUid;
    private String bookingTime;

    public DoctorUpcomingAppointments(String patientName, String patientUid, String bookingTime) {
        this.patientName = patientName;
        this.patientUid = patientUid;
        this.bookingTime = bookingTime;
    }

    public DoctorUpcomingAppointments() {
    }

    public String getPatientName() {
        return patientName;
    }
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    public String getPatientUid() {
        return patientUid;
    }
    public void setPatientUid(String patientUid) {
        this.patientUid = patientUid;
    }
    public String getBookingTime() {
        return bookingTime;
    }
    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }
}
