package net.concheese.concert.repository;

import net.concheese.concert.model.Concert;

import java.util.Optional;
import java.util.UUID;

public interface ConcertRepository {
    Optional<Concert> findById(UUID concertId);
    Concert insert(Concert concert);

}
