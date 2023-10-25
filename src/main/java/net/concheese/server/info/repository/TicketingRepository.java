package net.concheese.server.info.repository;

import net.concheese.server.info.model.Ticketing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface TicketingRepository extends JpaRepository<Ticketing, Long> {
}
