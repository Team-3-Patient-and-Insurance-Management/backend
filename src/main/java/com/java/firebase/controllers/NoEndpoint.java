package com.java.firebase.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class NoEndpoint {
    @PostMapping("/")
    public void noEndpoint() {
        System.out.println("No endpoint here");
    }
}
