package net.concheese.server.firebase.repository;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import net.concheese.server.concert.model.Concert;
import net.concheese.server.firebase.model.FirebaseUserToken;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@EnableJpaRepositories
public interface FirebaseTokenRepository extends JpaRepository<FirebaseUserToken, Long> {

}
