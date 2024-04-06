package com.java.firebase.model.Doctor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DoctorUpcomingAppointments {
    private String patientName;
    private String patientUid;
    private String bookingTime;
    private String closePhysicalContact;
    private String experiencedSymptoms;
    private String positiveCovid90Days;
    private String selfMonitor;
    private String wantCovidTest;
    private Date date;

    public DoctorUpcomingAppointments(String patientName, String patientUid, String bookingTime, String closePhysicalContact, String experiencedSymptoms, String positiveCovid90Days, String selfMonitor, String wantCovidTest, Date date) {
        this.patientName = patientName;
        this.patientUid = patientUid;
        this.bookingTime = bookingTime;
        this.closePhysicalContact = closePhysicalContact;
        this.experiencedSymptoms = experiencedSymptoms;
        this.positiveCovid90Days = positiveCovid90Days;
        this.selfMonitor = selfMonitor;
        this.wantCovidTest = wantCovidTest;
        this.date = date;
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

    public String getClosePhysicalContact() {
        return closePhysicalContact;
    }
    public void setClosePhysicalContact(String closePhysicalContact) {
        this.closePhysicalContact = closePhysicalContact;
    }
    public String getExperiencedSymptoms() {
        return experiencedSymptoms;
    }
    public void setExperiencedSymptoms(String experiencedSymptoms) {
        this.experiencedSymptoms = experiencedSymptoms;
    }
    public String getPositiveCovid90Days() {
        return positiveCovid90Days;
    }
    public void setPositiveCovid90Days(String positiveCovid90Days) {
        this.positiveCovid90Days = positiveCovid90Days;
    }
    public String getSelfMonitor() {
        return selfMonitor;
    }
    public void setSelfMonitor(String selfMonitor) {
        this.selfMonitor = selfMonitor;
    }
    public String getWantCovidTest() {
        return wantCovidTest;
    }
    public void setWantCovidTest(String wantCovidTest) {
        this.wantCovidTest = wantCovidTest;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(this.date);
    }
}
