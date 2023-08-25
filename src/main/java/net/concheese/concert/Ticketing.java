package net.concheese.concert;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ticketing {

  private long id;
  private String who; // 티켓팅 대상
  private String link; // 티켓팅 링크
  private LocalDateTime ticketingDate; // 티켓팅 날짜

  public Ticketing(long id, String who, String link, LocalDateTime ticketingDate) {
    this.id = id;
    this.who = who;
    this.link = link;
    this.ticketingDate = ticketingDate;
  }
}
