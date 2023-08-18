package net.concheese.server.user.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import net.concheese.server.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

  Optional<User> findByLoginId(String id);

  void deleteByLoginId(String id);

  List<User> findByName(String name);

}