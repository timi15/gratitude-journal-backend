package dev.timi15.gratitudejournal.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
@Slf4j
public class FirebaseConfig {

    @Value("classpath:config/gratitude-journal-7545e-firebase-adminsdk-fbsvc-a77ffd00c3.json")
    private Resource serviceAccount;

    @PostConstruct
    public void initializaFirebase() throws IOException {

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }

        log.info("Firebase config initialized");
    }

    public Firestore getFirebase(){
        return FirestoreClient.getFirestore();
    }

}
