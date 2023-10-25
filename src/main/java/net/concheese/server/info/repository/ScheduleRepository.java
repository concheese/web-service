package net.concheese.server.info.repository;

import net.concheese.server.info.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
