package com.java.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@SpringBootApplication
public class setUp {
    public static void main(String[] args) throws IOException {
        InputStream serviceAccount = setUp.class.getResourceAsStream("/serviceAccountKey.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        FirebaseApp.initializeApp(options);
        SpringApplication.run(setUp.class, args);
    }
}
