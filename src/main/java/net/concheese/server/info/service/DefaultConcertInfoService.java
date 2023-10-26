package net.concheese.server.info.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import net.concheese.server.info.converter.ConcertFormToConcert;
import net.concheese.server.info.dto.ConcertForm;
import net.concheese.server.info.model.Concert;
import net.concheese.server.info.model.ConcertType;
import net.concheese.server.info.model.Performer;
import net.concheese.server.info.repository.ConcertRepository;
import net.concheese.server.info.repository.PerformerRepository;
import net.concheese.server.info.repository.ScheduleRepository;
import net.concheese.server.info.repository.TicketingRepository;
import org.springframework.stereotype.Service;

/**
 * {@code DefaultConcertInfoService} 클래스는 {@code ConcertInfoService} 인터페이스의 기본 구현을 제공합니다. 이 서비스는 공연
 * 정보의 생성, 업데이트, 검색, 목록 조회 및 삭제와 관련된 작업을 처리합니다. 추가적으로, 공연 제목, 공연 유형, 또는 수행자 이름으로 공연을 검색하는 기능도
 * 포함됩니다.
 *
 * @author Lynn Choi
 * @author MyoungHa Ji
 * @since 2023-09-16
 */
@RequiredArgsConstructor
@Service
public class DefaultConcertInfoService implements ConcertInfoService {

  private final ConcertRepository concertRepository;
  private final PerformerRepository performerRepository;
  private final ScheduleRepository scheduleRepository;
  private final TicketingRepository ticketingRepository;
  private final ConcertFormToConcert concertFormToConcert;

  /**
   * 주어진 콘서트 엔터티를 저장하거나 업데이트합니다.
   *
   * @param concert 저장하거나 업데이트할 콘서트 엔터티.
   * @return 저장되거나 업데이트된 콘서트 엔터티.
   */
  @Override
  public Concert saveOrUpdate(Concert concert) {
    concertRepository.save(concert);
    return concert;
  }

  /**
   * 주어진 {@link ConcertForm}을 기반으로 콘서트를 저장하거나 업데이트합니다.
   *
   * @param concertForm 콘서트 정보를 포함하는 DTO.
   * @return 저장되거나 업데이트된 콘서트 엔터티.
   */
  @Override
  public Concert saveOrUpdateConcertForm(ConcertForm concertForm) {
    return saveOrUpdate(concertFormToConcert.convert(concertForm));
  }

  /**
   * 모든 콘서트의 목록을 검색합니다.
   *
   * @return 모든 콘서트의 목록.
   */
  @Override
  public List<Concert> listAll() {
    return new ArrayList<>(concertRepository.findAll());
  }

  /**
   * 특정 제목으로 콘서트 목록을 검색합니다.
   *
   * @param title 검색할 콘서트의 제목.
   * @return 주어진 제목의 콘서트 목록.
   */
  @Override
  public List<Concert> listAllByTitle(String title) {
    return concertRepository.findAllByTitle(title);
  }

  /**
   * 특정 유형의 콘서트 목록을 검색합니다.
   *
   * @param type 검색할 콘서트의 유형.
   * @return 주어진 유형의 콘서트 목록.
   */
  @Override
  public List<Concert> listAllByType(ConcertType type) {
    return new ArrayList<>(concertRepository.findAllByType(type));
  }

  /**
   * 이름으로 퍼포머와 관련된 콘서트 목록을 검색합니다.
   *
   * @param name 퍼포머의 이름.
   * @return 주어진 퍼포머 이름과 관련된 콘서트 목록.
   */
  @Override
  public List<Concert> listAllByPerformerName(String name) {
    Performer performer = performerRepository.findByName(name).orElse(null);
    List<Concert> allConcerts = listAll();
    return performer == null ? allConcerts
        : allConcerts.stream().filter(concert -> concert.getPerformers().contains(performer))
            .collect(Collectors.toList());
  }

  /**
   * 고유 식별자로 콘서트를 검색합니다.
   *
   * @param id 검색할 콘서트의 UUID.
   * @return 주어진 UUID의 콘서트 또는 찾을 수 없는 경우 null.
   */
  @Override
  public Concert getById(UUID id) {
    return concertRepository.findById(id).orElse(null);
  }

  /**
   * 고유 식별자로 콘서트를 삭제합니다. 관련된 일정과 티켓팅 정보도 함께 삭제합니다.
   *
   * @param id 삭제할 콘서트의 UUID.
   */
  @Override
  public void delete(UUID id) {
    concertRepository.findById(id).ifPresent(concert -> {
      scheduleRepository.deleteAll(concert.getSchedules());
      ticketingRepository.deleteAll(concert.getTicketings());
    });
    concertRepository.deleteById(id);
  }

}
