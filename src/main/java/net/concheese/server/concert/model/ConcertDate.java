package net.concheese.server.concert.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

/**
 * {@code ConcertDate} 클래스는 콘서트의 날짜 및 시간 정보를 나타내는 모델입니다.
 */
public class ConcertDate {
    private UUID dateID;
    private LocalDate startedAt;
    private LocalDate endDate;
    private LocalTime startTime;

    public ConcertDate() {
    }

    public ConcertDate(UUID dateID, LocalDate startedAt, LocalTime startTime) {
        this.dateID = dateID;
        this.startedAt = startedAt;
        this.startTime = startTime;
    }

    public ConcertDate(UUID dateID, LocalDate startedAt, LocalDate endDate, LocalTime startTime) {
        this.dateID = dateID;
        this.startedAt = startedAt;
        this.endDate = endDate;
        this.startTime = startTime;
    }

    public UUID getDateID() {
        return dateID;
    }

    public void setDateID(UUID dateID) {
        this.dateID = dateID;
    }

    public LocalDate getstartedAt() {
        return startedAt;
    }

    public void setStartDate(LocalDate startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    // TODO: 구현 필요
}