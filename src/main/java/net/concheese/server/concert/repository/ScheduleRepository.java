package net.concheese.server.concert.repository;

import net.concheese.server.concert.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.UUID;

@EnableJpaRepositories
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
