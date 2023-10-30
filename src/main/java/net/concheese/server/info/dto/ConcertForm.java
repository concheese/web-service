package net.concheese.server.info.dto;

import java.util.List;
import java.util.UUID;
import net.concheese.server.info.model.ConcertType;
import net.concheese.server.info.model.Performer;
import net.concheese.server.info.model.Schedule;
import net.concheese.server.info.model.Ticketing;

/**
 * {@code ConcertForm}은 공연 정보의 주요 양식을 나타내는 레코드입니다.
 * <p>
 * 이 레코드는 주로 클라이언트로부터 공연 정보를 수집하거나 전달할 때 사용됩니다. 각 필드는 공연의 주요 정보를 나타냅니다.
 * </p>
 *
 * @author Lynn Choi
 * @author MyoungHa Ji
 * @version 1.0
 * @see net.concheese.server.info.model.ConcertType
 * @see net.concheese.server.info.model.Performer
 * @see net.concheese.server.info.model.Schedule
 * @see net.concheese.server.info.model.Ticketing
 * @since 2023-09-16
 */
public record ConcertForm(UUID id, String title, ConcertType type, List<Schedule> schedules,
                          List<Ticketing> ticketings, String description, String link,
                          List<Performer> performers) {

}
