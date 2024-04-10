package com.java.firebase.controllers.Doctor;

import com.java.firebase.model.Doctor.DoctorRatings;
import com.java.firebase.model.Doctor.DoctorUpcomingAppointments;
import com.java.firebase.model.Patient.Patient;
import com.java.firebase.model.Patient.PatientAppointmentHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.java.firebase.service.Doctor.DoctorDashboardService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin
public class DoctorDashboardController {
    private final DoctorDashboardService doctorDashboardService;

    @Autowired
    public DoctorDashboardController(DoctorDashboardService doctorDashboardService) {
        this.doctorDashboardService = doctorDashboardService;
    }

    @GetMapping("/doctorCurrentAppointments")
    public List<DoctorUpcomingAppointments> getDoctorCurrentAppointments() throws ExecutionException, InterruptedException {
        List<DoctorUpcomingAppointments> appointments = doctorDashboardService.getDoctorUpcomingAppointments();
        return appointments;
    }

    @GetMapping("/patientDetails")
    public Patient getPatientDetails(@RequestParam String patientUid) throws ExecutionException, InterruptedException {
        return doctorDashboardService.getPatientDetails(patientUid);
    }

    @GetMapping("/doctorRatings")
    public List<DoctorRatings> getDoctorRatings() throws ExecutionException, InterruptedException {
        return doctorDashboardService.getDoctorRatings();
    }
}
