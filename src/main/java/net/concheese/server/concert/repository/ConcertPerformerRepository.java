package net.concheese.server.concert.repository;

import net.concheese.server.concert.model.Concert_Performer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface ConcertPerformerRepository extends JpaRepository<Concert_Performer, UUID> {
}
