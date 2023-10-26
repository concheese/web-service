package net.concheese.server.info.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import net.concheese.server.common.BaseEntity;

/**
 * concheese 어플리케이션에서 개최되는 각각의 공연를 나타내는 엔터티 클래스입니다.
 * <p>
 * 이 클래스는 공연의 제목, 유형, 일정, 티켓팅 정보, 설명, 링크 및 참여하는 연주자들에 대한 정보를 포함하고 있습니다.
 * </p>
 *
 * @author Lynn Choi
 * @author Ji Myoung Ha
 * @version 1.0
 * @since 2023-10-21
 */
@Entity
@Table(name = "concerts")
public class Concert extends BaseEntity {

  /**
   * 공연의 제목입니다.
   */
  @Column(name = "title")
  private String title;

  /**
   * 공연의 유형입니다. (예: 콘서트, 뮤지컬, 오케스트라 등)
   */
  @Column(name = "type")
  @Enumerated(EnumType.STRING)
  private ConcertType type;

  /**
   * 공연의 일정 목록입니다.
   */
  @OneToMany
  @Column(name = "schedules")
  private List<Schedule> schedules;

  /**
   * 공연의 티켓팅 정보 목록입니다.
   */
  @OneToMany
  @Column(name = "ticketings")
  private List<Ticketing> ticketings;

  /**
   * 공연에 대한 상세 설명입니다.
   */
  @Column(name = "description")
  private String description;

  /**
   * 공연 정보나 티켓 구매 등과 관련된 외부 링크입니다.
   */
  @Column(name = "link")
  private String link;

  /**
   * 공연에 참여하는 연주자나 아티스트의 목록입니다.
   */
  @OneToMany
  @Column(name = "performers")
  private List<Performer> performers;

}
