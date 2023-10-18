package net.concheese.server.concert.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import net.concheese.server.concert.model.*;


public interface ConcertRepository {

  /**
   * 생성된 콘서트 정보를 레포지토리에 저장합니다.
   *
   * @param concertInfo 저장될 콘서트 정보.
   * @return 생성된 {@link ConcertInfo}
   */
  ConcertInfo insert(ConcertInfo concertInfo);

  /**
   * 기존 콘서트 정보 항목을 업데이트합니다.
   *
   * @param infoId           업데이트할 콘서트 정보의 고유 식별자
   * @param title            업데이트할 콘서트의 제목.
   * @param genre            업데이트할 콘서트의 장르.
   * @param performers       업데이트할 콘서트의 공연자.
   * @param ticketing        업데이트할 콘서트 티켓 정보.
   * @param schedules        업데이트할 콘서트의 일정.
   * @param description      업데이트할 콘서트에 대한 설명.
   * @param link             업데이트할 콘서트와 관련된 링크.
   * @return 업데이트된 {@link ConcertInfo}.
   */

  ConcertInfo update(UUID infoId, String title, Genre genre, List<String> performers, List<Schedules> schedules,
                     List<ConcertTicketInfo> ticketing, String description, String link);

  /**
   * 고유 식별자로 콘서트 정보를 읽어옵니다.
   *
   * @param infoId 읽어올 콘서트 정보의 고유 식별자
   * @return 찾은 경우 {@link ConcertInfo} 찾지 못한 경우 {@code null}
   */
  ConcertInfo readById(UUID infoId);

  /**
   * 장르로 필터링된 콘서트 정보 항목 목록을 검색합니다
   *
   * @param genre 필터링할 장르
   * @return 해당 장르와 일치하는 {@link ConcertInfo} 항목 목록
   */
  List<ConcertInfo> readByGenre(Genre genre);

  /**
   * 모든 콘서트 정보 항목의 목록을 검색합니다
   *
   * @return 모든 사용 가능한 {@link ConcertInfo} 항목 목록
   */
  List<ConcertInfo> readAllInfo();

  /**
   * 고유 식별자로 콘서트 정보를 삭제합니다
   *
   * @param infoId 삭제할 콘서트 정보의 고유 식별자
   */
  void deleteInfo(UUID infoId);
}
