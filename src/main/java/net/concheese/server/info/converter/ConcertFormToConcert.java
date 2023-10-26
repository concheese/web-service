package net.concheese.server.info.converter;

import net.concheese.server.info.dto.ConcertForm;
import net.concheese.server.info.model.Concert;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ConcertFormToConcert implements Converter<ConcertForm, Concert> {

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
