package com.java.firebase.model.Patient;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PatientUpcomingAppointments {
    private String doctorName;
    private String doctorUid;
    private String time;

    public PatientUpcomingAppointments(String doctorName, String doctorUid, String time) {
        this.doctorName = doctorName;
        this.doctorUid = doctorUid;
        this.time = time;
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
}