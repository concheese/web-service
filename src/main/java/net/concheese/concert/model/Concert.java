package net.concheese.concert.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Concert {

  private final UUID concertId;
  private String title;
  private Genre genre;
  private Status status;
  private String description;
  private List<Performer> performers;
  private List<ConcertTime> concertTimes;
  private List<TicketingTime> ticketingTimes;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt; // TODO 모든 변수에 대하여 Setter 실행 시 업데이트 되어야 함

  private Concert(UUID concertId, String title, Genre genre, Status status, String description,
      List<Performer> performers, List<ConcertTime> concertTimes,
      List<TicketingTime> ticketingTimes) {
    this.concertId = concertId;
    this.title = title;
    this.genre = genre;
    this.status = status;
    this.performers = performers;
    this.concertTimes = concertTimes;
    this.ticketingTimes = ticketingTimes;
    this.description = description;
    this.createdAt = LocalDateTime.now();
    this.updatedAt = createdAt;
  }

}
