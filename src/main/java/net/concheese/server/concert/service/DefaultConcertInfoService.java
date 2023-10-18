package net.concheese.server.concert.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import net.concheese.server.concert.model.*;
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
  public Concert createInfo(String title, Type type, List<String> performers, List<Schedule> schedules,
                            List<Ticketing> ticketing, String description, String link) {

    Concert concert = new Concert(UUID.randomUUID(), title, type, description, link);

    for(String performer : performers){
      Optional<Performer> optionalPerformer = performersRepository.findByName(performer);
      Performer performer1;
      if(optionalPerformer.isEmpty()) {
          performer1 = new Performer(UUID.randomUUID(),performer); // 새로 등록되는 공연자인 경우 테이블에 새로 등록
          performersRepository.save(performer1);
      }else{
          performer1 = optionalPerformer.get();
      }
      Concert_Performer concertPerformer = new Concert_Performer(UUID.randomUUID(), concert.getId(), performer1.getId());
      concertPerformerRepository.save(concertPerformer);
    }

    for(Schedule schedule : schedules){
      ScheduleTable scheduleTable = new ScheduleTable(UUID.randomUUID(), schedule.getTimestamp(), schedule.getPostal(), concert.getId());
      scheduleRepository.save(scheduleTable);
    }

    for(Ticketing ticket : ticketing){
      TicketingTable ticketingTable = new TicketingTable(UUID.randomUUID(), ticket.getStart(), ticket.getEnd(), ticket.getStatus(), concert.getId());
      ticketingRepository.save(ticketingTable);
    }

    return concertRepository.save(concert);
  }

//  @Override
//  public Concert updateInfo(UUID infoId, String title, Genre genre, List<String> performers, List<Schedule> schedules,
//                            List<Ticketing> ticketing, String description, String link) {
//    return concertRepository.update(infoId, title, genre, performers, schedules,ticketing, description, link);
//  }
//
//  @Override
//  public Concert readInfo(UUID infoId) {
//    return concertRepository.readById(infoId);
//  }
//
//  @Override
//  public List<Concert> readInfoList(Genre genre) {
//    return concertRepository.readByGenre(genre);
//  }
//
//  @Override
//  public List<Concert> readAllInfo() {
//    return concertRepository.readAllInfo();
//  }
//
//  @Override
//  public void deleteInfo(UUID infoId) {
//    concertRepository.deleteInfo(infoId);
//  }
}
