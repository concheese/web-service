package net.concheese.server.concert.repository;

import net.concheese.server.concert.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class DefaultConcertRepository implements ConcertRepository{

    @Override
    public ConcertInfo insert(ConcertInfo concertInfo) {
        // TODO: 구현필요
        return null;
    }

    @Override
    public ConcertInfo update(UUID infoId, String title, Genre genre, Location location, ConcertTicketInfo concertTicketInfo, ConcertTicketInfo ticketing, ConcertDate concertDate, String description, String link) {
        // TODO: 구현필요
        return null;
    }

    @Override
    public ConcertInfo readById(UUID InfoId) {
        // TODO: 구현필요
        return null;
    }

    @Override
    public List<ConcertInfo> readInfoList(Genre genre) {
        // TODO: 구현필요
        return null;
    }

    @Override
    public List<ConcertInfo> readAllInfo() {
        // TODO: 구현필요
        return null;
    }

    @Override
    public void deleteInfo(UUID infoId) {
        // TODO: 구현필요
    }
}
