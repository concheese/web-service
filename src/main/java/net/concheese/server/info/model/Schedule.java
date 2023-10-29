package net.concheese.server.info.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import net.concheese.server.common.BaseEntity;

/**
 * concheese 어플리케이션에서 문화 활동의 일정을 나타내는 엔터티입니다.
 * <p>
 * 이 엔터티는 각 문화 활동의 일정 및 위치에 대한 정보를 캡슐화합니다. {@code Schedule} 엔터티는 {@code BaseEntity}를 상속받아 유니버설 유니크
 * 식별자(UUID)를 주 키로 사용합니다.
 * </p>
 * <p>
 * {@code Entity} 주석은 이 클래스가 JPA 엔터티임을 나타내며, {@code Table} 주석은 엔터티와 연관된 데이터베이스 테이블 이름을 지정합니다.
 * </p>
 *
 * @author Lynn Choi
 * @author MyoungHa Ji
 * @version 1.0
 * @see java.time.LocalDateTime
 * @see net.concheese.server.common.BaseEntity
 * @since 2023-10-21
 */
@Getter
@Setter
@Entity
@Table(name = "schedules")
public class Schedule extends BaseEntity {

  /**
   * 문화 활동의 예정된 날짜 및 시간입니다.
   */
  @Column(name = "datetime")
  private LocalDateTime dateTime;

  /**
   * 문화 활동이 진행되는 장소의 우편 번호입니다.
   */
  @Column(name = "postal_code")
  private int postalCode;

}
