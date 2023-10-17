package net.concheese.server.concert.controller;

import net.concheese.server.concert.model.ConcertDate;
import net.concheese.server.concert.model.ConcertTicketInfo;
import net.concheese.server.concert.model.Genre;
import net.concheese.server.concert.model.Schedules;

import java.util.List;

/**
 * {@code ConcertInfoRequest}는 콘서트 정보를 생성 또는 업데이트하기 위한 HTTP 요청 데이터를 나타내는 레코드입니다.
 *
 * @version 1.0
 * @since 2023-09-16
 */
public record ConcertInfoRequest(String title, Genre genre, List<String> performers, Schedules schedules,
                                 List<ConcertTicketInfo> ticketing, String description, String link) {

  /**
   * 생성자를 통해 ConcertInfoRequest 객체를 생성합니다.
   *
   * @param title        콘서트 제목.
   * @param genre        콘서트 장르.
   * @param performers   콘서트 공연자 정보.
   * @param ticketing    티켓 정보.
   * @param description  콘서트 설명.
   * @param link         콘서트 관련 링크.
   */
  public ConcertInfoRequest {
  }

}
