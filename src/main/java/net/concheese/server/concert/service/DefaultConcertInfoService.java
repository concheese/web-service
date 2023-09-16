package net.concheese.server.concert.service;

import java.util.List;
import java.util.UUID;
import net.concheese.server.concert.model.ConcertDate;
import net.concheese.server.concert.model.ConcertInfo;
import net.concheese.server.concert.model.ConcertTicketInfo;
import net.concheese.server.concert.model.Genre;
import net.concheese.server.concert.model.Location;
import org.springframework.stereotype.Service;

/**
 * {@code DefaultConcertInfoService} 클래스는 {@code ConcertInfoService} 인터페이스의 기본 구현을 제공합니다. 이 서비스는 콘서트
 * 정보의 생성, 업데이트, 검색, 목록 조회 및 삭제와 관련된 작업을 처리합니다.
 *
 * @since 2023-09-16
 */
@Service
public class DefaultConcertInfoService implements ConcertInfoService {

  @Override
  public ConcertInfo createInfo(String title, Genre genre, Location location,
      ConcertTicketInfo concertTicketing, ConcertTicketInfo ticketing, ConcertDate concertDate,
      String description, String link) {
    // TODO: 구현 필요
    return null;
  }

  @Override
  public ConcertInfo updateInfo(UUID infoId, String title, Genre genre, Location location,
      ConcertTicketInfo concertTicketInfo, ConcertTicketInfo ticketing, ConcertDate concertDate,
      String description, String link) {
    // TODO: 구현 필요
    return null;
  }

  @Override
  public ConcertInfo readInfo(UUID infoId) {
    // TODO: 구현 필요
    return null;
  }

  @Override
  public List<ConcertInfo> readInfoList(Genre genre) {
    // TODO: 구현 필요
    return null;
  }

  @Override
  public List<ConcertInfo> readAllInfo() {
    // TODO: 구현 필요
    return null;
  }

  @Override
  public void deleteInfo(UUID infoId) {
    // TODO: 구현 필요
  }
}
