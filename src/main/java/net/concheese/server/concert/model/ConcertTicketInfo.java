package net.concheese.server.concert.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

/**
 * {@code ConcertTicketInfo} 클래스는 콘서트 티켓 정보를 나타내는 모델입니다.
 */
public class ConcertTicketInfo {
    private UUID ticketingID;
    private LocalDate startedAt;
    private LocalDate endAt;
    private LocalTime startTime;
    private TicketingType type;

    public ConcertTicketInfo() {
    }

    public LocalDate getEndAt() {
        return endAt;
    }

    public LocalDate getStartedAt() {
        return startedAt;
    }

    public ConcertTicketInfo(UUID ticketingID, LocalDate startedAt, LocalDate endAt, LocalTime startTime, TicketingType type) {
        this.ticketingID = ticketingID;
        this.startedAt = startedAt;
        this.endAt = endAt;
        this.startTime = startTime;
        this.type = type;
    }

    public UUID getTicketingID() {
        return ticketingID;
    }

    public void setTicketingID(UUID ticketingID) {
        this.ticketingID = ticketingID;
    }

    public void setstartedAt(LocalDate startedAt) {
        this.startedAt = startedAt;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public TicketingType getType() {
        return type;
    }

    public void setType(TicketingType type) {
        this.type = type;
    }

    public ConcertTicketInfo(UUID ticketingID, LocalDate startedAt, LocalTime startTime, TicketingType type) {
        this.ticketingID = ticketingID;
        this.startedAt = startedAt;
        this.startTime = startTime;
        this.type = type;
    }

    public ConcertTicketInfo(LocalDate startedAt,LocalTime startTime, TicketingType type) {
        this.ticketingID = UUID.randomUUID();
        this.startedAt = startedAt;
        this.startTime = startTime;
        this.type = type;
    }
}
