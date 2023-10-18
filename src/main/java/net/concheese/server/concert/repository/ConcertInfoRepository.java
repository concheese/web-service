package net.concheese.server.concert.repository;

import net.concheese.server.concert.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ConcertInfoRepository implements ConcertRepository{
    @Override
    public ConcertInfo insert(ConcertInfo concertInfo) {
        return null;
    }

    @Override
    public ConcertInfo update(UUID infoId, String title, Genre genre, List<String> performers, List<Schedules> schedules, List<ConcertTicketInfo> ticketing, String description, String link) {
        return null;
    }


    @Override
    public ConcertInfo readById(UUID infoId) {
        return null;
    }

    @Override
    public List<ConcertInfo> readByGenre(Genre genre) {
        return null;
    }

    @Override
    public List<ConcertInfo> readAllInfo() {
        return null;
    }

    @Override
    public void deleteInfo(UUID infoId) {

    }
}
