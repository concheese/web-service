package net.concheese.server.info.controller;

import net.concheese.server.info.model.Performer;
import net.concheese.server.info.model.Ticketing;
import net.concheese.server.info.model.Schedule;
import net.concheese.server.concert.model.Type;

import java.util.List;

/**
 * {@code ConcertInfoRequest}는 콘서트 정보를 생성 또는 업데이트하기 위한 HTTP 요청 데이터를 나타내는 레코드입니다.
 *
 * @version 1.0
 * @since 2023-09-16
 */
public record ConcertInfoRequest(String title, Type type, List<Performer> performers, List<Schedule> schedule,
                                 List<Ticketing> ticketing, String description, String link) {

  /**
   * 생성자를 통해 ConcertInfoRequest 객체를 생성합니다.
   *
   * @param title        콘서트 제목.
   * @param type        콘서트 유형.
   * @param performers   콘서트 공연자 정보.
   * @param schedule    콘서트 일정.
   * @param ticketing    티켓 정보.
   * @param description  콘서트 설명.
   * @param link         콘서트 관련 링크.
   */
  public ConcertInfoRequest {
  }

}
