package net.concheese.server.concert.repository;

import net.concheese.server.concert.model.Performer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories // repository 를 스프링 데이터 JPA 에서 인식할 수 있도록 합니다.
public interface PerformersRepository extends JpaRepository<Performer, Long> {
    public Optional<Performer> findByName(String performerName);
}
