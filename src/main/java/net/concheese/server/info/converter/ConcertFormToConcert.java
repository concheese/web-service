package net.concheese.server.info.converter;

import net.concheese.server.info.dto.ConcertForm;
import net.concheese.server.info.model.Concert;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * {@code ConcertForm} 레코드를 {@code Concert} 엔터티로 변환하는 컨버터입니다.
 * <p>
 * 이 컴포넌트는 Spring Framework의 타입 변환 시스템을 사용하여 {@code ConcertForm}에서 제공되는 정보를 {@code Concert} 엔터티 객체로
 * 매핑하고 반환합니다.
 * </p>
 *
 * @author MyoungHa Ji
 * @version 1.0
 * @see net.concheese.server.info.dto.ConcertForm
 * @see net.concheese.server.info.model.Concert
 * @since 2023-10-26
 */
@Component
public class ConcertFormToConcert implements Converter<ConcertForm, Concert> {

  /**
   * 주어진 {@code ConcertForm}의 데이터를 기반으로 {@code Concert} 엔터티를 생성하고 반환합니다.
   *
   * @param form 변환하려는 {@code ConcertForm} 레코드
   * @return 변환된 {@code Concert} 엔터티
   */
  @Override
  public Concert convert(ConcertForm form) {
    Concert concert = new Concert();
    if (form.id() != null) {
      concert.setId(form.id());
    }
    concert.setTitle(form.title());
    concert.setType(form.type());
    concert.setSchedules(form.schedules());
    concert.setTicketings(form.ticketings());
    concert.setDescription(form.description());
    concert.setLink(form.link());
    concert.setPerformers(form.performers());
    return concert;
  }
  
}
