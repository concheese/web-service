package net.concheese.concert.service;

import net.concheese.concert.model.Concert;
import net.concheese.concert.repository.ConcertRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ConcertService {
    private ConcertRepository concertRepository;

    public ConcertService(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    public Concert getConcert(UUID concertId){
        return concertRepository
                .findById(concertId)
                .orElseThrow(()-> new RuntimeException("Can not find a Concert for " + concertId));
    }
}
