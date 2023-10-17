package net.concheese.server.concert.model;

import java.time.LocalDateTime;
import java.util.*;
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
  private java.util.List<String> performers;
  private Schedules schedules;
  private List<ConcertTicketInfo> ticketing;
  private String description;
  private String link;
  private LocalDateTime created_at;
  private LocalDateTime updated_at;

  public ConcertInfo(UUID infoId, String title, Genre genre, List<String> performers, Schedules schedules, List<ConcertTicketInfo> ticketing, String description, String link) {
    this.infoId = infoId;
    this.title = title;
    this.genre = genre;
    this.performers = performers;
    this.schedules = schedules;
    this.ticketing = ticketing;
    this.description = description;
    this.link = link;
  }

  public UUID getInfoId() {
    return infoId;
  }

  public String getTitle() {
    return title;
  }

  public Genre getGenre() {
    return genre;
  }

  public List<String> getPerformers() {
    return performers;
  }

  public Schedules getSchedules() {
    return schedules;
  }

  public List<ConcertTicketInfo> getTicketing() {
    return ticketing;
  }

  public String getDescription() {
    return description;
  }

  public String getLink() {
    return link;
  }

  public LocalDateTime getCreated_at() {
    return created_at;
  }

  public LocalDateTime getUpdated_at() {
    return updated_at;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  public void setPerformers(List<String> performers) {
    this.performers = performers;
  }

  public void setSchedules(Schedules schedules) {
    this.schedules = schedules;
  }

  public void setTicketing(List<ConcertTicketInfo> ticketing) {
    this.ticketing = ticketing;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public void setCreated_at(LocalDateTime created_at) {
    this.created_at = created_at;
  }

  public void setUpdated_at(LocalDateTime updated_at) {
    this.updated_at = updated_at;
  }
}


