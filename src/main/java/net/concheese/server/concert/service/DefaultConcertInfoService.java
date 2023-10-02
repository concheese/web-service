package net.concheese.server.concert.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import net.concheese.server.concert.model.ConcertDate;
import net.concheese.server.concert.model.ConcertInfo;
import net.concheese.server.concert.model.ConcertTicketInfo;
import net.concheese.server.concert.model.Genre;
import net.concheese.server.concert.model.Location;
import net.concheese.server.concert.repository.DefaultConcertRepository;
import org.springframework.stereotype.Service;

/**
 * {@code DefaultConcertInfoService} 클래스는 {@code ConcertInfoService} 인터페이스의 기본 구현을 제공합니다. 이 서비스는 콘서트
 * 정보의 생성, 업데이트, 검색, 목록 조회 및 삭제와 관련된 작업을 처리합니다.
 *
 * @since 2023-09-16
 */
@Service
public class DefaultConcertInfoService implements ConcertInfoService {

  private final DefaultConcertRepository concertRepository;

  public DefaultConcertInfoService(DefaultConcertRepository concertRepository) {
    this.concertRepository = concertRepository;
  }

  @Override
  public ConcertInfo createInfo(String title, Genre genre, String location, String artist,
      ConcertTicketInfo preTicketing, ConcertTicketInfo ticketing, ConcertDate concertDate,
      String description, String link) {

    Location location1 = new Location(UUID.randomUUID(),1,1,1,location);
    ConcertTicketInfo preT = new ConcertTicketInfo(UUID.randomUUID(),preTicketing.getstartedAt(), preTicketing.getStartTime(),preTicketing.getType());
    ConcertTicketInfo Ticket = new ConcertTicketInfo(UUID.randomUUID(),preTicketing.getstartedAt(), preTicketing.getStartTime(),preTicketing.getType());
    ConcertDate concertDate1 = new ConcertDate(UUID.randomUUID(), concertDate.getStartDate(),null, concertDate.getStartTime());

    ConcertInfo concertInfo = new ConcertInfo(UUID.randomUUID(), title, genre, location1, artist,
            preT, Ticket, concertDate1, description, link);
    return concertRepository.insert(concertInfo);
  }

  @Override
  public ConcertInfo updateInfo(UUID infoId, String title, Genre genre, String location, String artist,
      ConcertTicketInfo concertTicketInfo, ConcertTicketInfo ticketing, ConcertDate concertDate,
      String description, String link) {
    Location location1 = new Location(UUID.randomUUID(),1,1,1,location);
    return concertRepository.update(infoId, title, genre, location1, artist, concertTicketInfo, ticketing,
        concertDate, description, link);
  }

  @Override
  public ConcertInfo readInfo(UUID infoId) {
    return concertRepository.readById(infoId);
  }

  @Override
  public List<ConcertInfo> readInfoList(Genre genre) {
    return concertRepository.readByGenre(genre);
  }

  @Override
  public List<ConcertInfo> readAllInfo() {
    return concertRepository.readAllInfo();
  }

  @Override
  public void deleteInfo(UUID infoId) {
    concertRepository.deleteInfo(infoId);
  }
}
