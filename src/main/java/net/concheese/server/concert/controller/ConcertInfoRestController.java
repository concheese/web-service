package net.concheese.server.concert.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.concheese.server.concert.model.ConcertInfo;
import net.concheese.server.concert.model.Genre;
import net.concheese.server.concert.service.ConcertInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code ConcertInfoRestController}는 콘서트 정보 관련 HTTP 요청을 처리하는 컨트롤러입니다.
 *
 * @version 1.0
 * @since 2023-09-16
 */
@RestController
@RequestMapping(value = "/api/v1/concert")
@RequiredArgsConstructor
public class ConcertInfoRestController {

  private final ConcertInfoService concertInfoService;

  /**
   * 콘서트 정보를 생성합니다.
   *
   * @param request 생성할 콘서트 정보의 요청 데이터.
   * @return 생성된 콘서트 정보를 담은 ResponseEntity.
   */
  @PostMapping("/info")
  public ResponseEntity<ConcertInfo> createInfo(@RequestBody ConcertInfoRequest request) {
    return ResponseEntity.ok(
        concertInfoService.createInfo(request.title(), request.genre(), request.location(), request.artist(),
            request.preTicketing(), request.ticketing(), request.concertDate(),
            request.description(), request.link()));
  }

  @PostMapping("/echo")
  public ResponseEntity<String> infoString(@RequestBody ConcertInfoRequest request) {
    return ResponseEntity.ok(request.toString());
  }

  /**
   * 콘서트 정보를 업데이트합니다.
   *
   * @param request 콘서트 정보 업데이트를 위한 요청 데이터.
   * @param infoId  업데이트할 콘서트 정보의 식별자.
   * @return 업데이트된 콘서트 정보를 담은 ResponseEntity.
   */
  @PutMapping("/info/{infoId}")
  public ResponseEntity<ConcertInfo> updateInfo(@RequestBody ConcertInfoRequest request,
      @PathVariable String infoId) {
    return ResponseEntity.ok(
        concertInfoService.updateInfo(UUID.fromString(infoId), request.title(), request.genre(),
            request.location(), request.artist(), request.preTicketing(), request.ticketing(), request.concertDate(),
            request.description(), request.link()));
  }

  /**
   * 특정 콘서트 정보를 조회합니다.
   *
   * @param infoId 조회할 콘서트 정보의 식별자.
   * @return 조회된 콘서트 정보를 담은 ResponseEntity.
   */
  @GetMapping("/info/{infoId}")
  public ResponseEntity<ConcertInfo> readInfo(@PathVariable String infoId) {
    return ResponseEntity.ok(concertInfoService.readInfo(UUID.fromString(infoId)));
  }

  /**
   * 콘서트 정보 목록을 조회합니다.
   *
   * @param genre 필터링할 콘서트 장르 (옵션).
   * @return 조회된 콘서트 정보 목록을 담은 ResponseEntity.
   */
  @GetMapping("/infoList")
  public ResponseEntity<List<ConcertInfo>> readInfoList(@RequestParam Optional<Genre> genre) {
    return ResponseEntity.ok(
        genre.map(concertInfoService::readInfoList).orElse(concertInfoService.readAllInfo()));
  }

  /**
   * 콘서트 정보를 삭제합니다.
   *
   * @param infoId 삭제할 콘서트 정보의 식별자.
   * @return 삭제 성공 여부를 나타내는 ResponseEntity.
   */
  @DeleteMapping("/info/{infoId}")
  public ResponseEntity<Void> deleteInfo(@PathVariable String infoId) {
    concertInfoService.deleteInfo(UUID.fromString(infoId));
    return ResponseEntity.ok().build();
  }
}
