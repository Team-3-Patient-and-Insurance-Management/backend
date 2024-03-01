package com.java.firebase.controllers;

import com.java.firebase.model.Doctor;
import com.java.firebase.service.DoctorSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class DoctorSearchController {

    @Autowired
    DoctorSearchService doctorSearchService;

    @GetMapping("/searchDoctors")
    public ResponseEntity<List<Doctor>> searchDoctors(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String specialization,
            @RequestParam(required = false) Boolean covidSupport,
            @RequestParam(required = false) String availableTime
    ) {
        try {
            List<Doctor> doctorNames = doctorSearchService.searchDoctors(name, specialization, covidSupport != null && covidSupport);
            return ResponseEntity.ok(doctorNames);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/doctorDetails")
    public ResponseEntity<Doctor> getDoctorDetails(@RequestParam String doctorUid) {
        try {
            Doctor doctor = doctorSearchService.getDoctorDetails(doctorUid);
            if (doctor != null) {
                return ResponseEntity.ok(doctor);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
