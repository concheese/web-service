package net.concheese.server.concert.model;

import java.util.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

/**
 * {@code ConcertInfo} 클래스는 콘서트 정보를 나타내는 모델입니다.
 */

@Entity
public class Concert {
  @Getter @Column(name="id") @Id
  private final UUID id;
  @Getter @Setter @Column(name="title")
  private String title;
  @Getter @Setter @Column(name="type")
  private Type type;
//  @Getter @Setter @Column(name="performers")
//  private List<String> performers;
//  @Getter @Setter @Column(name="schedules")
//  private List<Schedule> schedules;
//  @Getter @Setter @Column(name="ticketing")
//  private List<Ticketing> ticketing;
  @Getter @Setter @Column(name="description")
  private String description;
  @Getter @Setter @Column(name="link")
  private String link; // url 저장 방법 찾아보기

  public Concert(UUID id, String title, Type type, String description, String link) {
    this.id = id;
    this.title = title;
    this.type = type;
//    this.performers = performers;
//    this.schedules = schedules;
//    this.ticketing = ticketing;
    this.description = description;
    this.link = link;
  }
}


