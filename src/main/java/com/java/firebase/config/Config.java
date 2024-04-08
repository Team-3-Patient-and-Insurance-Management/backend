package com.java.firebase.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class Config implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "https://localhost:3000",
                        "http://localhost:3000",
                        "http://localhost:3001",
                        "https://localhost:3001",
                        "localhost:3000",
                        "localhost:3001",
                        "https://patientinsurancemanagement.web.app",
                        "https://patientinsurancemanagement.firebaseapp.com",
                        "https://team-3-patient-and-insurance-management.github.io/frontend/",
                        "https://team-3-patient-and-insurance-management.github.io"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
