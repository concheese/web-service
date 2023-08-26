package net.concheese.concert.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
public class ConcertTime {
    private UUID concertTimeId;
    private LocalDateTime startTime;
    private LocalDateTime endTime; // 필요할까?
    private Location location;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ConcertTime(UUID concertTimeId, LocalDateTime startTime, LocalDateTime endTime, Location location) {
        this.concertTimeId = concertTimeId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }
    // TODO 공연 시간 모델 작성

}
