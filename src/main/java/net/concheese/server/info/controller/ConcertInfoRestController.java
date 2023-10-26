package net.concheese.server.info.controller;

import java.util.List;
import java.util.UUID;
import net.concheese.server.info.dto.ConcertForm;
import net.concheese.server.info.model.Concert;
import net.concheese.server.info.model.ConcertType;
import net.concheese.server.info.service.ConcertInfoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code ConcertInfoRestController} 클래스는 콘서트 정보에 관한 RESTful API를 제공합니다.
 * <p>
 * 이 컨트롤러는 콘서트의 등록, 업데이트, 목록 조회, 개별 콘서트의 정보 조회 및 삭제와 같은 엔드포인트를 제공합니다. 각 메서드는
 * {@code ConcertInfoService}를 사용하여 기본 로직을 처리합니다.
 * </p>
 * <p>
 * API의 기본 경로는 "/api/v1/info/concert"로 설정되어 있습니다.
 * </p>
 *
 * @author Lynn Choi
 * @author MyoungHa Ji
 * @since 2023-09-16
 */
@RestController
@RequestMapping(value = "/api/v1/info/concert")
public class ConcertInfoRestController {

  private final ConcertInfoService concertInfoService;

  public ConcertInfoRestController(ConcertInfoService concertInfoService) {
    this.concertInfoService = concertInfoService;
  }

  /**
   * 새로운 콘서트를 등록합니다.
   *
   * @param request 콘서트 등록 정보를 담은 요청 객체.
   * @return 등록된 콘서트 객체.
   */
  @RequestMapping("/register")
  public Concert newConcert(@RequestBody ConcertForm request) {
    return concertInfoService.saveOrUpdateConcertForm(request);
  }

  /**
   * 기존 콘서트 정보를 업데이트합니다.
   *
   * @param request 콘서트 업데이트 정보를 담은 요청 객체.
   * @return 업데이트된 콘서트 객체.
   */
  @RequestMapping("/update")
  public Concert updateConcert(@RequestBody ConcertForm request) {
    return concertInfoService.saveOrUpdateConcertForm(request);
  }

  /**
   * 조건에 맞는 콘서트 목록을 반환합니다. 제목, 유형 또는 연주자 이름별로 필터링 할 수 있습니다.
   *
   * @param title         제목을 기준으로 필터링 할 경우 사용.
   * @param type          유형을 기준으로 필터링 할 경우 사용.
   * @param performerName 연주자 이름을 기준으로 필터링 할 경우 사용.
   * @return 조건에 맞는 콘서트 목록.
   */
  @RequestMapping("/list")
  public List<Concert> listConcerts(@RequestParam(value = "title", required = false) String title,
      @RequestParam(value = "type", required = false) String type,
      @RequestParam(value = "performer", required = false) String performerName) {
    List<Concert> concerts = concertInfoService.listAll();
    if (title != null) {
      concerts.retainAll(concertInfoService.listAllByTitle(title));
    }
    if (type != null) {
      concerts.retainAll(concertInfoService.listAllByType(ConcertType.valueOf(type)));
    }
    if (performerName != null) {
      concerts.retainAll(concertInfoService.listAllByPerformerName(performerName));
    }
    return concerts;
  }

  /**
   * 고유 식별자로 콘서트 정보를 반환합니다.
   *
   * @param id 조회하려는 콘서트의 UUID 문자열.
   * @return 해당 식별자를 가진 콘서트 객체.
   */
  @RequestMapping("/{id}")
  public Concert getConcert(@PathVariable String id) {
    return concertInfoService.getById(UUID.fromString(id));
  }

  /**
   * 고유 식별자로 콘서트를 삭제합니다.
   *
   * @param id 삭제하려는 콘서트의 UUID 문자열.
   */
  @RequestMapping("/delete/{id}")
  public void deleteConcert(@PathVariable String id) {
    concertInfoService.delete(UUID.fromString(id));
  }
}
