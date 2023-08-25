package net.concheese.concert;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConcertTime {

  private long id;
  private String location;
  private LocalDateTime dateTime;

  public ConcertTime(Long id, String location, LocalDateTime dateTime) {
    this.id = id;
    this.location = location;
    this.dateTime = dateTime;
  }

}
