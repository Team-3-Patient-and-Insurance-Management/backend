package com.java.firebase.controllers;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.java.firebase.model.Patient;
import com.java.firebase.model.Twilio2FAService;
import com.java.firebase.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class Twilio2FAControllerTest {

    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
    public static final String VERIFY_SERVICE_SID = System.getenv("TWILIO_VERIFY_SERVICE_SID");

    @Mock
    private Twilio2FAService twilio2FAService;

    @InjectMocks
    private Twilio2FAController twilio2FAController;

    private static UserRecord userRecord;

    private static final String SERVICE_ACCOUNT_KEY_PATH = "src/main/resources/serviceAccountKey.json";
//    @Test
//    void generateOTPSuccess() {
//        // Given
//        String testPhoneNumber = "+18125985490";
//        doNothing().when(twilio2FAService).generateOTP(testPhoneNumber);
//
//        // When
//        ResponseEntity<String> response = twilio2FAController.generateOTP(testPhoneNumber);
//
//        // Then
//        verify(twilio2FAService, times(1)).generateOTP(testPhoneNumber);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).contains("Your OTP has been sent");
//    }

//    @Test
//    void verifyUserOTPSuccess() {
//        // Given
//        String testPhoneNumber = "+18125985490";
//        when(twilio2FAService.verifyUserOTP(testPhoneNumber, "123456")).thenReturn(true);
//
//        // When
//        ResponseEntity<String> response = twilio2FAController.verifyUserOTP(testPhoneNumber, "123456");
//
//        // Then
//        verify(twilio2FAService, times(1)).verifyUserOTP(testPhoneNumber, "123456");
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).contains("Verification successful");
//    }
//
//    @Test
//    void verifyUserOTPFailure() {
//        // Given
//        String testPhoneNumber = "+18125985490";
//        when(twilio2FAService.verifyUserOTP(testPhoneNumber, "wrong_code")).thenReturn(false);
//
//        // When
//        ResponseEntity<String> response = twilio2FAController.verifyUserOTP(testPhoneNumber, "wrong_code");
//
//        // Then
//        verify(twilio2FAService, times(1)).verifyUserOTP(testPhoneNumber, "wrong_code");
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//        assertThat(response.getBody()).contains("Verification failed");
//    }
//    @BeforeAll
//    static void init() throws IOException, FirebaseAuthException {
//        FileInputStream serviceAccount =
//                new FileInputStream(SERVICE_ACCOUNT_KEY_PATH);
//
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .setDatabaseUrl("https://patientinsurancemanagement.firebaseio.com")
//                .build();
//
//        FirebaseApp.initializeApp(options);
//
//        FirebaseAuth auth = FirebaseAuth.getInstance();
//        String email = "svelet@iu.edu";
//        try {
//            UserRecord user = auth.getUserByEmail(email);
//
//            FirebaseAuth.getInstance().deleteUser(user.getUid());
//        } catch (Exception e) {
//            System.out.println();
//        }
//
//        UserRecord.CreateRequest userRequest = new UserRecord.CreateRequest()
//                .setEmail("svelet@iu.edu")
//                .setPassword("password")
//                .setPhoneNumber("+18125985490")
//                .setDisplayName("Sasha Velet");
//
//        userRecord = FirebaseAuth.getInstance().createUser(userRequest);
//    }
//
//    @Test
//    void generateOTPSuccess() {
//        // Arrange
//        doNothing().when(twilio2FAService).generateOTP(anyString());
//
//        // Act
//        ResponseEntity<String> response = twilio2FAController.generateOTP(userRecord.getPhoneNumber());
//
//        // Assert
//        verify(twilio2FAService, times(1)).generateOTP(userRecord.getPhoneNumber());
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).contains("Your OTP has been sent");
//    }
//
//    @Test
//    void verifyUserOTPSuccess() throws Exception {
//        // Arrange
//        when(twilio2FAService.verifyUserOTP(anyString(), anyString())).thenReturn(true);
//
//        // Act
//        ResponseEntity<String> response = twilio2FAController.verifyUserOTP(userRecord, "123456");
//
//        // Assert
//        verify(twilio2FAService, times(1)).verifyUserOTP(userRecord.getPhoneNumber(), "123456");
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).contains("Verification successful");
//    }
//
//    @Test
//    void verifyUserOTPFailure() throws Exception {
//        // Arrange
//        when(twilio2FAService.verifyUserOTP(anyString(), anyString())).thenReturn(false);
//
//        // Act
//        ResponseEntity<String> response = twilio2FAController.verifyUserOTP(userRecord, "wrong_code");
//
//        // Assert
//        verify(twilio2FAService, times(1)).verifyUserOTP(userRecord.getPhoneNumber(), "wrong_code");
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//        assertThat(response.getBody()).contains("Verification failed");
//    }
}