package net.concheese.server.concert.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

/**
 * {@code ConcertDate} 클래스는 콘서트의 날짜 및 시간 정보를 나타내는 모델입니다.
 */
public class ConcertDate {
    private UUID dateID;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;

    public ConcertDate() {
    }

    public ConcertDate(UUID dateID, LocalDate startDate, LocalTime startTime) {
        this.dateID = dateID;
        this.startDate = startDate;
        this.startTime = startTime;
    }

    public ConcertDate(UUID dateID, LocalDate startDate, LocalDate endDate, LocalTime startTime) {
        this.dateID = dateID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
    }

    public UUID getDateID() {
        return dateID;
    }

    public void setDateID(UUID dateID) {
        this.dateID = dateID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
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