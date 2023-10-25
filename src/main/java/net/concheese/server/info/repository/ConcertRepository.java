package net.concheese.server.info.repository;

import net.concheese.server.info.model.Concert;
import net.concheese.server.concert.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface ConcertRepository extends JpaRepository<Concert, Long> {
    List<Concert> findByType(Type genre);
}
