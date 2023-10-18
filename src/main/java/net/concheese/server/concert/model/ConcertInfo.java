package net.concheese.server.concert.model;

import java.time.LocalDateTime;
import java.util.*;

import lombok.*;

/**
 * {@code ConcertInfo} 클래스는 콘서트 정보를 나타내는 모델입니다.
 */
@Data
public class ConcertInfo {

  private final UUID infoId;
  private String title;
  private Genre genre;
  private java.util.List<String> performers;
  private List<Schedules> schedules;
  private List<ConcertTicketInfo> ticketing;
  private String description;
  private String link;
  private LocalDateTime created_at;
  private LocalDateTime updated_at;

  public ConcertInfo(UUID infoId, String title, Genre genre, List<String> performers, List<Schedules> schedules, List<ConcertTicketInfo> ticketing, String description, String link) {
    this.infoId = infoId;
    this.title = title;
    this.genre = genre;
    this.performers = performers;
    this.schedules = schedules;
    this.ticketing = ticketing;
    this.description = description;
    this.link = link;
  }

}


