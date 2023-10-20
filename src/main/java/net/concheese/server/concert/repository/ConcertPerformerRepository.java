package net.concheese.server.concert.repository;

import net.concheese.server.concert.model.Concert;
import net.concheese.server.concert.model.Performer;
import net.concheese.server.concert.model.PerformerConcert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface ConcertPerformerRepository extends JpaRepository<PerformerConcert, Long> {
    List<PerformerConcert> findByPerformer(Performer performer);
}
