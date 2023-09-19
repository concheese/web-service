package net.concheese.server.concert.model;

import java.util.UUID;

/**
 * {@code ConcertInfo} 클래스는 콘서트 정보를 나타내는 모델입니다.
 */
public class ConcertInfo {
    private final UUID infoID;
    String title;
    Genre genre;
    Location location;
    ConcertTicketInfo concertTicketing;
    ConcertTicketInfo ticketing;
    ConcertDate concertDate;
    String description;
    String link;

    public ConcertInfo(UUID infoID, String title, Genre genre, Location location,
                       ConcertTicketInfo concertTicketing, ConcertTicketInfo ticketing,
                       ConcertDate concertDate, String description, String link) {
        this.infoID = infoID;
        this.title = title;
        this.genre = genre;
        this.location = location;
        this.concertTicketing = concertTicketing;
        this.ticketing = ticketing;
        this.concertDate = concertDate;
        this.description = description;
        this.link = link;
    }
    // TODO: 구현 필요
}
