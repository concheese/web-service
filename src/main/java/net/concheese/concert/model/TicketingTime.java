package net.concheese.concert.model;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.UUID;

        @Getter
        @Setter
public class TicketingTime {
    private UUID ticketingTimeId;
    private LocalDateTime ticketingStartTime;
    private Target target;
    private URL url;
    private LocalDateTime createdAt;

            public TicketingTime(UUID ticketingTimeId, LocalDateTime ticketingStartTime, Target target, URL url, LocalDateTime createdAt, LocalDateTime updatedAt) {
                this.ticketingTimeId = ticketingTimeId;
                this.ticketingStartTime = ticketingStartTime;
                this.target = target;
                this.url = url;
                this.createdAt = createdAt;
                this.updatedAt = updatedAt;
            }

            private LocalDateTime updatedAt;

  // TODO 공연 티켓팅 시간 모델 작성
  
}
