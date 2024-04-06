package com.java.firebase.controllers.InsuranceProvider;

import com.java.firebase.model.InsuranceProvider.InsurancePlan;
import com.java.firebase.service.InsuranceProvider.InsurancePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/insurance-plans")
public class InsurancePlanController {

    private final InsurancePlanService insurancePlanService;

    @Autowired
    public InsurancePlanController(InsurancePlanService insurancePlanService) {
        this.insurancePlanService = insurancePlanService;
    }

    @GetMapping
    public List<InsurancePlan> getAllInsurancePlans() {
        return insurancePlanService.getAllInsurancePlans();
    }


    @PostMapping("/choose")
    public void chooseInsurancePlan(@RequestParam String userId, @RequestParam String planId) {
        insurancePlanService.chooseInsurancePlan(userId, planId);
    }
}
