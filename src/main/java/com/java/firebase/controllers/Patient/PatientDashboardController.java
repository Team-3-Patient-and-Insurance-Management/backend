package com.java.firebase.controllers.Patient;

import com.java.firebase.model.Patient.PatientInsuranceProviders;
import com.java.firebase.model.Patient.PatientUpcomingAppointments;
import com.java.firebase.service.Patient.PatientDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin
public class PatientDashboardController {
    private final PatientDashboardService patientDashboardService;

    @Autowired
    public PatientDashboardController(PatientDashboardService patientDashboardService) {
        this.patientDashboardService = patientDashboardService;
    }

    @GetMapping("/patientUpcomingAppointments")
    public List<PatientUpcomingAppointments> getPatientUpcomingAppointments() throws ExecutionException, InterruptedException {
        return patientDashboardService.getPatientUpcomingAppointments();
    }

    @GetMapping("/patientInsuranceProviders")
    public List<PatientInsuranceProviders> getPatientInsuranceProviders() throws ExecutionException, InterruptedException {
        return patientDashboardService.getPatientInsuranceProviders();
    }

}
