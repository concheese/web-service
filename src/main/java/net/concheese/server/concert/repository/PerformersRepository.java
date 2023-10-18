package net.concheese.server.concert.repository;

import net.concheese.server.concert.model.Performer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerformersRepository extends JpaRepository<Performer, Long> {
    public Optional<Performer> findByName(String performerName);
}
