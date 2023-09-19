package net.concheese.server.concert.model;

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
  }

    public Object getPostId() {
    }

  public Object getCategory() {
    return category;
  }

  public void setCategory(Object category) {
    this.category = category;
  }
}
