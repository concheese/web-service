package net.concheese.server.info.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import net.concheese.server.common.BaseEntity;

/**
 * concheese 어플리케이션에서 문화 활동의 티켓팅 정보를 나타내는 엔터티입니다.
 * <p>
 * 이 엔터티는 문화 활동의 티켓팅 일정 및 상태를 정의합니다. 각 티켓팅에는 시작 시간, 종료 시간 및 판매 상태 정보가 포함됩니다.
 * </p>
 *
 * @author Lynn Choi
 * @author MyoungHa Ji
 * @version 1.0
 * @since 2023-10-21
 */
@Getter
@Setter
@Entity
@Table(name = "ticketings")
public class Ticketing extends BaseEntity {

  /**
   * 티켓팅의 시작 시간을 나타냅니다.
   */
  @Column(name = "start")
  private LocalDateTime start;

  /**
   * 티켓팅의 종료 시간을 나타냅니다.
   */
  @Column(name = "end")
  private LocalDateTime end;

  /**
   * 현재 티켓팅의 판매 상태를 나타냅니다. 상태 값은 {@link TicketingStatus} 열거형에 정의되어 있습니다.
   */
  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private TicketingStatus status;

}
