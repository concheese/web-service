package net.concheese.server.info.service;

import java.util.List;
import java.util.UUID;
import net.concheese.server.info.dto.ConcertForm;
import net.concheese.server.info.model.Concert;
import net.concheese.server.info.model.ConcertType;

/**
 * {@code Concert} 엔터티에 대한 서비스 계층의 인터페이스입니다.
 * <p>
 * 이 인터페이스는 {@code Concert} 관련 작업에 대한 핵심 비즈니스 로직을 정의합니다. 여기에는 공연 정보의 생성, 수정, 조회 및 삭제와 같은 기본 CRUD 작업이
 * 포함됩니다.
 * </p>
 *
 * @author Lynn Choi
 * @author MyoungHa Ji
 * @version 1.0
 * @see net.concheese.server.info.model.Concert
 * @see net.concheese.server.info.dto.ConcertForm
 * @since 2023-09-16
 */
public interface ConcertInfoService {

  /**
   * 제공된 공연 정보를 저장하거나 업데이트합니다.
   *
   * @param concert 저장하거나 업데이트할 공연 정보.
   * @return 저장된 공연 정보.
   */
  Concert saveOrUpdate(Concert concert);

  /**
   * 제공된 공연 양식을 사용하여 공연 정보를 저장하거나 업데이트합니다.
   *
   * @param concertForm 저장하거나 업데이트할 공연 양식.
   * @return 저장된 공연 정보.
   */
  Concert saveOrUpdateConcertForm(ConcertForm concertForm);

  /**
   * 모든 공연 정보를 반환합니다.
   *
   * @return 모든 공연 정보의 목록.
   */
  List<Concert> listAll();

  /**
   * 주어진 공연 제목으로 모든 공연을 검색합니다.
   *
   * @param title 공연 제목
   * @return 해당 제목의 공연 목록
   */
  List<Concert> listAllByTitle(String title);

  /**
   * 주어진 공연 유형으로 모든 공연를 검색합니다.
   *
   * @param type 공연 유형
   * @return 해당 유형의 공연 목록
   */
  List<Concert> listAllByType(ConcertType type);

  /**
   * 주어진 수행자의 이름으로 모든 공연을 검색합니다.
   *
   * @param name 수행자 이름
   * @return 해당 수행자 이름의 공연 목록
   */
  List<Concert> listAllByPerformerName(String name);

  /**
   * 지정된 ID로 공연 정보를 반환합니다.
   *
   * @param id 조회할 공연 정보의 UUID.
   * @return 지정된 ID를 가진 공연 정보.
   */
  Concert getById(UUID id);

  /**
   * 지정된 ID의 공연 정보를 삭제합니다.
   *
   * @param id 삭제할 공연 정보의 UUID.
   */
  void delete(UUID id);

}
