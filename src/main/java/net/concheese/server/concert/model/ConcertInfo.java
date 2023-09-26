package net.concheese.server.concert.model;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 * {@code ConcertInfo} 클래스는 콘서트 정보를 나타내는 모델입니다.
 */
@Getter
@Setter
public class ConcertInfo {

  private final UUID infoId;
  private String title;
  private Genre genre;
  private Location location;
  private ConcertTicketInfo concertTicketing;
  private ConcertTicketInfo ticketing;
  private ConcertDate concertDate;
  private String description;
  private String link;
  private LocalDateTime created_at;
  private LocalDateTime updated_at;

  public ConcertInfo(UUID infoId, String title, Genre genre, Location location,
                     ConcertTicketInfo concertTicketing, ConcertTicketInfo ticketing, ConcertDate concertDate,
                     String description, String link) {
    this.infoId = infoId;
    this.title = title;
    this.genre = genre;
    this.location = location;
    this.concertTicketing = concertTicketing;
    this.ticketing = ticketing;
    this.concertDate = concertDate;
    this.description = description;
    this.link = link;
    this.created_at = LocalDateTime.now();
    this.updated_at = LocalDateTime.now();
  }

  public UUID getInfoId() {
    return infoId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Genre getGenre() {
    return genre;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public ConcertTicketInfo getConcertTicketing() {
    return concertTicketing;
  }

  public void setConcertTicketing(ConcertTicketInfo concertTicketing) {
    this.concertTicketing = concertTicketing;
  }

  public ConcertTicketInfo getTicketing() {
    return ticketing;
  }

  public void setTicketing(ConcertTicketInfo ticketing) {
    this.ticketing = ticketing;
  }

  public ConcertDate getConcertDate() {
    return concertDate;
  }

  public void setConcertDate(ConcertDate concertDate) {
    this.concertDate = concertDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }
  public LocalDateTime getCreated_at() {
    return created_at;
  }
  public LocalDateTime getUpdated_at() {
    return updated_at;
  }
}


