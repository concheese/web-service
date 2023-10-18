package net.concheese.server.concert.repository;

import net.concheese.server.concert.model.ScheduleTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<ScheduleTable, UUID> {
}
