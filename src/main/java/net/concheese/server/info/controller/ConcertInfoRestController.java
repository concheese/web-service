package net.concheese.server.info.controller;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import net.concheese.server.info.model.Concert;
import net.concheese.server.concert.model.Type;
import net.concheese.server.info.service.ConcertInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
  @Autowired
  private final ConcertInfoService concertInfoService;

  /**
   * 콘서트 정보를 생성합니다.
   *
   * @param request 생성할 콘서트 정보의 요청 데이터.
   * @return 생성된 콘서트 정보를 담은 ResponseEntity.
   */
  @PostMapping("/info")
  public ResponseEntity<Concert> createInfo(@RequestBody ConcertInfoRequest request) {
    return ResponseEntity.ok(
        concertInfoService.createInfo(request.title(), request.type(), request.performers(), request.schedule(), request.ticketing(),
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
  public ResponseEntity<Concert> updateInfo(@RequestBody ConcertInfoRequest request,
                                            @PathVariable long infoId) {
    return ResponseEntity.ok(
            concertInfoService.updateInfo(infoId, request.title(), request.type(), request.performers(), request.schedule(), request.ticketing(),
                    request.description(), request.link()));
  }

  /**
   * 특정 콘서트 정보를 조회합니다.
   *
   * @param infoId 조회할 콘서트 정보의 식별자.
   * @return 조회된 콘서트 정보를 담은 ResponseEntity.
   */
  @GetMapping("/info/{infoId}")
  public ResponseEntity<Concert> readInfo(@PathVariable long infoId) {
    return ResponseEntity.ok(concertInfoService.readInfo(infoId).orElse(null));
  }

  /**
   * 콘서트 정보 목록을 조회합니다.
   *
   * @param genre 필터링할 콘서트 장르 (옵션).
   * @return 조회된 콘서트 정보 목록을 담은 ResponseEntity.
   */
  @GetMapping("/infoListByGenre")
  public ResponseEntity<List<Concert>> readInfoListByGenre(@RequestParam Optional<Type> genre) {
    return ResponseEntity.ok(concertInfoService.readInfoListByGenre(genre.orElse(null)));
  }

  @GetMapping("/infoListByPerformer")
  public ResponseEntity<List<Concert>> readInfoListByPerformer(@RequestParam Optional<String> performerName){
    return ResponseEntity.ok(concertInfoService.readInfoListByPerformer(performerName.orElse(null)));
  }

  // 공연날짜, 공연장 별로 필터링 하는 기능 추가해보기.

  @GetMapping("/readAllInfo")
    public ResponseEntity<List<Concert>> readAllInfo(){
        return ResponseEntity.ok(concertInfoService.readAllInfo());
    }
  /**
   * 콘서트 정보를 삭제합니다.
   *
   * @param infoId 삭제할 콘서트 정보의 식별자.
   * @return 삭제 성공 여부를 나타내는 ResponseEntity.
   */
  @DeleteMapping("/info/{infoId}")
  public ResponseEntity<Void> deleteInfo(@PathVariable long infoId) {
    concertInfoService.deleteInfo(infoId);
    return ResponseEntity.ok().build();
  }
}
