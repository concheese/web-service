package net.concheese.server.firebase.repository;

import net.concheese.server.firebase.model.FirebaseUserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface FirebaseTokenRepository extends JpaRepository<FirebaseUserToken, Long> {

}
