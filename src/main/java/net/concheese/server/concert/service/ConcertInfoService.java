package net.concheese.server.concert.service;

import net.concheese.server.concert.model.*;

import java.util.List;
import java.util.Optional;

/**
 * {@code ConcertInfoService} 인터페이스는 콘서트 정보를 관리하는 데 필요한 작업을 정의합니다.
 *
 * @since 2023-09-16
 */
public interface ConcertInfoService {

  /**
   * 새로운 콘서트 정보 항목을 생성합니다.
   *
   * @return 생성된 {@link Concert}.
   */
  Concert createInfo(String title, Type type, List<Performer> performers, List<Schedule> schedule, List<Ticketing> ticketing, String description, String link);

  /**
   * 기존 콘서트 정보 항목을 업데이트합니다.
   *
   * @param infoId            업데이트할 콘서트 정보의 고유 식별자.
   * @param title            콘서트의 제목.
   * @param type            콘서트의 장르.
   * @param performers `      콘서트의 공연자.
   * @param ticketing        콘서트 티켓 정보.
   * @param schedules        콘서트의 일정.
   * @param description      콘서트에 대한 설명.
   * @param link             콘서트와 관련된 링크.
   * @return 업데이트된 {@link Concert}.
   */
  Concert updateInfo(long infoId, String title, Type type, List<Performer> performers, List<Schedule> schedules,
                     List<Ticketing> ticketing, String description, String link);

  /**
   * 고유 식별자로 콘서트 정보를 읽어옵니다.
   *
   * @param infoId 읽어올 콘서트 정보의 고유 식별자.
   * @return 찾은 경우 {@link Concert} 또는 찾지 못한 경우 {@code null}.
   */
  Optional<Concert> readInfo(long infoId);

  /**
   * 장르로 필터링된 콘서트 정보 항목 목록을 검색합니다.
   *
   * @param type 필터링할 장르.
   * @return 해당 장르와 일치하는 {@link Concert} 항목 목록.
   */
  List<Concert> readInfoListByGenre(Type type);

  List<Concert> readInfoListByPerformer(String performer);

  /**
   * 모든 콘서트 정보 항목의 목록을 검색합니다.
   *
   * @return 모든 사용 가능한 {@link Concert} 항목 목록.
   */
  List<Concert> readAllInfo();

  /**
   * 고유 식별자로 콘서트 정보를 삭제합니다.
   *
   * @param infoId 삭제할 콘서트 정보의 고유 식별자.
   */
  void deleteInfo(long infoId);
}
