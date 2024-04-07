package com.java.firebase.controllers.InsuranceProvider;

import com.java.firebase.model.InsuranceProvider.Clients;
import com.java.firebase.model.InsuranceProvider.InsurancePlan;
import com.java.firebase.service.InsuranceProvider.InsurancePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class InsurancePlanController {

    private final InsurancePlanService insurancePlanService;

    @Autowired
    public InsurancePlanController(InsurancePlanService insurancePlanService) {
        this.insurancePlanService = insurancePlanService;
    }

    @GetMapping("/getInsurancePlans")
    public List<InsurancePlan> getAllInsurancePlans() throws ExecutionException, InterruptedException {
        return insurancePlanService.getAllInsurancePlans();
    }

    @GetMapping("/getClients")
    public List<Clients> getAllClients() throws ExecutionException, InterruptedException {
        return insurancePlanService.getAllClients();
    }

    @PostMapping("/addInsurancePlan")
    public void addInsurancePlan(@RequestBody InsurancePlan insurancePlan) throws ExecutionException, InterruptedException {
        insurancePlanService.addInsurancePlan(insurancePlan.getPlanName(), insurancePlan.getDescription(), insurancePlan.getPremium(), insurancePlan.getDeductible(), insurancePlan.isMedicalCoverage(), insurancePlan.isDentalCoverage(), insurancePlan.isVisionCoverage());
    }
}