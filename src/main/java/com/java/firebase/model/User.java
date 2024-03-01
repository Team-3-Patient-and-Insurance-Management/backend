package com.java.firebase.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {

    // Personal Information
    private String firstName;
    private String lastName;
    private String fullName;
    private String gender;
    private String phoneNumber;
    private Date dateOfBirth;

    // Address Information
    private String streetAddress;
    private String country;
    private String state;
    private String city;
    private String zipCode;

    // Additional fields based on role
    private String role;

    // Doctor fields
    private String doctorLicense;
    private String specialization;
    private Boolean supportCovid;
    private List<String> appointmentTimes;

    // Insurance Provider fields
    private String company;
    private String companyLicense;
    private Boolean is2FAEnabled;
    private Boolean isPhoneNumberVerified;

    public User() {
    }

    public User(String firstName, String lastName, String gender, String phoneNumber, Date dateOfBirth,
                String streetAddress, String country, String state, String city, String zipCode,
                String role, String doctorLicense, String specialization, Boolean supportCovid,
                List<String> appointmentTimes, String company, String companyLicense,
                Boolean is2FAEnabled, Boolean isPhoneNumberVerified) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.streetAddress = streetAddress;
        this.country = country;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
        this.role = role;
        this.doctorLicense = doctorLicense;
        this.specialization = specialization;
        this.supportCovid = supportCovid;
        this.appointmentTimes = appointmentTimes != null ? appointmentTimes : new ArrayList<>();
        this.company = company;
        this.companyLicense = companyLicense;
        this.is2FAEnabled = is2FAEnabled;
        this.isPhoneNumberVerified = isPhoneNumberVerified;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getDoctorLicense() {
        return doctorLicense;
    }
    public void setDoctorLicense(String doctorLicense) {
        this.doctorLicense = doctorLicense;
    }
    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    public Boolean getSupportCovid() {
        return supportCovid;
    }
    public void setSupportCovid(Boolean supportCovid) {
        this.supportCovid = supportCovid;
    }
    public List<String> getAppointmentTimes() {
        return appointmentTimes;
    }

    public void setAppointmentTimes(List<String> appointmentTimes) {
        this.appointmentTimes = appointmentTimes;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getCompanyLicense() {
        return companyLicense;
    }
    public void setCompanyLicense(String companyLicense) {
        this.companyLicense = companyLicense;
    }

    // Get the underlying UserRecord

    public Boolean getIs2FAEnabled() {
        return is2FAEnabled;
    }
    public void setIs2FAEnabled(Boolean is2FAEnabled) {
        this.is2FAEnabled = is2FAEnabled;
    }
    public Boolean getPhoneNumberVerified() {
        return isPhoneNumberVerified;
    }
    public void setPhoneNumberVerified(Boolean phoneNumberVerified) {
        isPhoneNumberVerified = phoneNumberVerified;
    }
}
