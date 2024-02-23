package com.java.firebase.model;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    // Getters and Setters for additional fields
    private String phoneNumber;
    private String token;
    private String firstName;
    private String lastName;
    private String address;
    private Boolean is2FAEnabled;
    private Boolean isPhoneNumberVerified;

    public User() {
    }

    // Constructor
    public User(String phoneNumber, String firstName, String lastName, String address) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        is2FAEnabled = true;
        isPhoneNumberVerified = false;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
