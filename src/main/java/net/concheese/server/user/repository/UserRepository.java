package net.concheese.server.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import net.concheese.server.user.model.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLoginId(String loginId);
    Optional<User> findByEmail(String email);
    void deleteUserByloginId(String loginId);
}

