package com.java.firebase.model.Patient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PatientUpcomingAppointments {
    private String doctorName;
    private String doctorUid;
    private String time;
    private Date date;

    public PatientUpcomingAppointments(String doctorName, String doctorUid, String time, Date date) {
        this.doctorName = doctorName;
        this.doctorUid = doctorUid;
        this.time = time;
        this.date = date;
    }

    public PatientUpcomingAppointments() {
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorUid() {
        return doctorUid;
    }

    public void setDoctorUid(String doctorUid) {
        this.doctorUid = doctorUid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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