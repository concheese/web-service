package net.concheese.server.concert.repository;

import net.concheese.server.concert.model.Ticketing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.UUID;

@EnableJpaRepositories
public interface TicketingRepository extends JpaRepository<Ticketing, Long> {
}
