package net.concheese.server.concert.model;

import java.util.*;

import jakarta.persistence.*;
import lombok.*;

/**
 * {@code ConcertInfo} 클래스는 콘서트 정보를 나타내는 모델입니다.
 */
@Getter
@Entity
@Table(name="concerts")
public class Concert {
  @Getter @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Getter @Setter @Column(name="title")
  private String title;
  @Getter @Setter @Column(name="type")
  private Type type;
  @Getter @Setter
  @OneToMany(mappedBy = "concert", cascade = CascadeType.ALL, orphanRemoval = true) // mappedBy = "concert" 를 통해 Concert 객체의 schedules 필드를 참조한다는 것을 알려줌
  private List<Schedule> schedule;
  @Getter @Setter
  @OneToMany(mappedBy = "concert", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Ticketing> ticketing;
  @Getter @Setter @Column(name="description")
  private String description;
  @Getter @Setter @Column(name="link")
  private String link; // url 저장 방법 찾아보기
  @Getter @Setter
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
    name = "concert_performer",
    joinColumns = @JoinColumn(name = "concert_id"),
    inverseJoinColumns = @JoinColumn(name = "performer_id")) // concert_performer 테이블을 생성하고, concert_id와 performer_id를 외래키로 갖는다.
  private List<Performer> performers;

  public Concert(String title, Type type, List<Schedule> schedule, List<Ticketing> ticketing, String description, String link, List<Performer> performers) {
    this.title = title;
    this.type = type;
    this.schedule = schedule;
    this.ticketing = ticketing;
    this.description = description;
    this.link = link;
    this.performers = performers;
  }
}


