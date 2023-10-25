package net.concheese.server.info.repository;

import net.concheese.server.info.model.Performer;
import net.concheese.server.info.model.PerformerConcert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface ConcertPerformerRepository extends JpaRepository<PerformerConcert, Long> {
    List<PerformerConcert> findByPerformer(Performer performer);
}
