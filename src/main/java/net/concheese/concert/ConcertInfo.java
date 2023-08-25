package net.concheese.concert;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConcertInfo {

  private long id;
  private String title;
  private String genre;
  private String performers;
  private List<ConcertTime> time;
  private List<Ticketing> ticketing;
  private String description;

  public ConcertInfo(long id, String title, String genre, String location, String performers,
      List<ConcertTime> time, List<Ticketing> ticketing, String description) {
    this.id = id;
    this.title = title;
    this.genre = genre;
    this.time = time;
    this.performers = performers;
    this.ticketing = ticketing;
    this.description = description;
  }
  
}


