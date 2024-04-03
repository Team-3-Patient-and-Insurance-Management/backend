package com.java.firebase.controllers;
import com.google.firebase.auth.UserRecord;
import com.java.firebase.model.User;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "/2FA")
@Slf4j
public class Twilio2FAController {
    // Initialize with your Twilio credentials
    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
    public static final String VERIFY_SERVICE_SID = System.getenv("TWILIO_VERIFY_SERVICE_SID");

    @GetMapping(value = "/generateOTP/{phoneNumber}")
    public static String generateOTP(@PathVariable String phoneNumber){
        System.out.println(ACCOUNT_SID + " " + AUTH_TOKEN + " " + VERIFY_SERVICE_SID);
        System.out.println(System.getenv("TWILIO_ACCOUNT_SID") + " " + System.getenv("TWILIO_AUTH_TOKEN") + " " + System.getenv("TWILIO_VERIFY_SERVICE_SID"));

        Twilio.init(System.getenv("TWILIO_ACCOUNT_SID"), System.getenv("TWILIO_AUTH_TOKEN"));


        Verification verification = Verification.creator(
                        VERIFY_SERVICE_SID, // this is the verification sid
                        "+" + phoneNumber, //this is the Twilio verified recipient phone number
                        "sms") // this is the channel type
                .create();


        System.out.println(verification.getStatus());

        return verification.getStatus();
    }

    @GetMapping("/verifyOTP/{phoneNumber}/{codeReceived}")
    public static String verifyOTP(@PathVariable String phoneNumber, @PathVariable String codeReceived) throws Exception {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        VerificationCheck verificationCheck;
        try {

            verificationCheck = VerificationCheck.creator(
                            VERIFY_SERVICE_SID)
                    .setTo("+" + phoneNumber)
                    .setCode(codeReceived)
                    .create();

            System.out.println(verificationCheck.getStatus());

        } catch (Exception e) {
            return "failed";
        }
        return verificationCheck.getStatus();
    }

}