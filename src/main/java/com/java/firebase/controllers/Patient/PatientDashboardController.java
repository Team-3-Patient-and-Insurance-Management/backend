package com.java.firebase.controllers.Patient;

import com.java.firebase.model.Doctor.DoctorRatings;
import com.java.firebase.model.InsuranceProvider.InsurancePlan;
import com.java.firebase.model.InsuranceProvider.InsuranceProvider;
import com.java.firebase.model.Patient.PatientInsuranceProviders;
import com.java.firebase.model.Patient.PatientUpcomingAppointments;
import com.java.firebase.service.Patient.PatientDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @GetMapping("/allInsuranceProviders")
    public List<InsuranceProvider> getAllInsuranceProviders() throws ExecutionException, InterruptedException {
        return patientDashboardService.getAllInsuranceProviders();
    }

    @PostMapping("/addInsuranceProvider")
    public void addInsuranceProvider(@RequestParam String insuranceProviderUid, @RequestBody InsurancePlan insurancePlan) throws ExecutionException, InterruptedException {
        patientDashboardService.addInsuranceProvider(insuranceProviderUid, insurancePlan.getPlanId(), insurancePlan.getPlanName(), insurancePlan.getDescription(), insurancePlan.getPremium(), insurancePlan.getDeductible(), insurancePlan.isMedicalCoverage(), insurancePlan.isDentalCoverage(), insurancePlan.isVisionCoverage());
    }

    @PostMapping("/postDoctorReview")
    public void postDoctorReview(@RequestParam String doctorUid,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                                 @RequestParam String time,
                                 @RequestBody DoctorRatings doctorRatings) throws ExecutionException, InterruptedException {
        patientDashboardService.postDoctorReview(doctorUid, date, time, doctorRatings.getTitle(), doctorRatings.getReview(), doctorRatings.getStars());
    }


}
