package net.concheese.server.info.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.concheese.server.concert.model.*;
import net.concheese.server.info.model.Concert;
import net.concheese.server.info.model.Performer;
import net.concheese.server.info.model.PerformerConcert;
import net.concheese.server.info.model.Schedule;
import net.concheese.server.info.model.Ticketing;
import net.concheese.server.info.repository.ConcertPerformerRepository;
import net.concheese.server.info.repository.ConcertRepository;
import net.concheese.server.info.repository.PerformersRepository;
import net.concheese.server.info.repository.ScheduleRepository;
import net.concheese.server.info.repository.TicketingRepository;
import org.springframework.stereotype.Service;
import net.concheese.server.concert.repository.*;

/**
 * {@code DefaultConcertInfoService} 클래스는 {@code ConcertInfoService} 인터페이스의 기본 구현을 제공합니다. 이 서비스는 콘서트
 * 정보의 생성, 업데이트, 검색, 목록 조회 및 삭제와 관련된 작업을 처리합니다.
 *
 * @since 2023-09-16
 */
@Service
public class DefaultConcertInfoService implements ConcertInfoService{
  private final ConcertRepository concertRepository;
  private final PerformersRepository performersRepository;
  private final ConcertPerformerRepository concertPerformerRepository;
  private final ScheduleRepository scheduleRepository;
  private final TicketingRepository ticketingRepository;

  public DefaultConcertInfoService(ConcertRepository concertRepository, PerformersRepository performersRepository, ConcertPerformerRepository concertPerformerRepository, ScheduleRepository scheduleRepository, TicketingRepository ticketingRepository) {
    this.concertRepository = concertRepository;
    this.performersRepository = performersRepository;
    this.concertPerformerRepository = concertPerformerRepository;
    this.scheduleRepository = scheduleRepository;
    this.ticketingRepository = ticketingRepository;
  }

  @Override
  public Concert createInfo(String title, Type type, List<Performer> inputPerformers, List<Schedule> schedules,
                            List<Ticketing> ticketing, String description, String link) {
    List<Performer> performers = new ArrayList<>();
    Concert concert = new Concert(title, type, schedules, ticketing, description, link, inputPerformers);
    for (Performer performer : inputPerformers) {
      Optional<Performer> existingPerformer = performersRepository.findByName(performer.getName());
      if (existingPerformer.isPresent()) {
        performers.add(existingPerformer.get());
      } else {
        performers.add(performer);
      }
    }
    concert.setPerformers(performers);

    return concertRepository.save(concert);
  }

  @Override
  public Concert updateInfo(long id, String title, Type type, List<Performer> inputPerformers, List<Schedule> schedules,
                            List<Ticketing> ticketing, String description, String link) {
    Concert concert = concertRepository.findById(id).get();
    concert.setTitle(title);
    concert.setType(type);
    concert.setSchedule(schedules);
    concert.setTicketing(ticketing);
    concert.setDescription(description);
    concert.setLink(link);
    concert.setPerformers(inputPerformers);
    return concertRepository.save(concert);
  }

  @Override
  public Optional<Concert> readInfo(long infoId) {
    return concertRepository.findById(infoId);
  }

  @Override
  public List<Concert> readInfoListByGenre(Type genre) {
    return concertRepository.findByType(genre);
  }


  @Override
    public List<Concert> readInfoListByPerformer(String name) {
    // 괜찮은 방법인지 확인 필요
    Performer performer = performersRepository.findByName(name).get();
    List<PerformerConcert> connections = concertPerformerRepository.findByPerformer(performer);
    List<Concert> concerts = new ArrayList<>();
    for (PerformerConcert connection : connections) {
      concerts.add(connection.getConcert());
    }
    return concerts;
  }

  @Override
  public List<Concert> readAllInfo() {
    return concertRepository.findAll();
  }

  @Override
  public void deleteInfo(long infoId) {
    concertRepository.deleteById(infoId);
    // 삭제 했을 때 연관된 티켓팅, 스케줄, 공연자연결 정보도 삭제되는지 DB 확인 필요.
    // Performer 테이블의 공연자 정보는 삭제되지 않아야 함.
  }
}
