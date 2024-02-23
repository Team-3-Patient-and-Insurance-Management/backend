package com.java.firebase.model;

public interface Twilio2FAService {
    void generateOTP(String phoneNumber); // Method to send verification code
    boolean verifyUserOTP(String phoneNumber, String code); // Method to verify user OTP
}
