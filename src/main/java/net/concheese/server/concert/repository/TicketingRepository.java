package net.concheese.server.concert.repository;

import net.concheese.server.concert.model.TicketingTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketingRepository extends JpaRepository<TicketingTable, UUID> {
}
