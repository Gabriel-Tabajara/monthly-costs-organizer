package com.monthlycostsorganizer.api.firebase;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import jakarta.annotation.PostConstruct;

import java.io.FileInputStream;

@Configuration
public class FirebaseConfig {

    @Value("${spring.database.url}")
    private String databaseUrl;

    @PostConstruct
    public void initialize() {


        try {

            FileInputStream serviceAccount = new FileInputStream("src/main/java/com/monthlycostsorganizer/api/firebase/serviceAccountKey.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(this.databaseUrl)
                    .build();

            FirebaseApp.initializeApp(options);

        } catch (Exception error) {
            error.printStackTrace();
        }

    }

    @Bean
    public DatabaseReference firebaseDatabase() {
        FirebaseApp app = FirebaseApp.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance(app);
        DatabaseReference databaseReference = database.getReference();
        return databaseReference;
    }
}
