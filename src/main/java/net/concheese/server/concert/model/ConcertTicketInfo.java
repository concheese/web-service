package net.concheese.server.concert.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

/**
 * {@code ConcertTicketInfo} 클래스는 콘서트 티켓 정보를 나타내는 모델입니다.
 */
public class ConcertTicketInfo {
    private UUID ticketingID;
    private LocalDate startDate;
    private LocalTime startTime;
    private TicketingType type;

    public UUID getTicketingID() {
        return ticketingID;
    }

    public void setTicketingID(UUID ticketingID) {
        this.ticketingID = ticketingID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
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

    public ConcertTicketInfo(UUID ticketingID, LocalDate startDate, LocalTime startTime, TicketingType type) {
        this.ticketingID = ticketingID;
        this.startDate = startDate;
        this.startTime = startTime;
        this.type = type;
    }

    public ConcertTicketInfo(LocalDate startDate,LocalTime startTime, TicketingType type) {
        this.ticketingID = UUID.randomUUID();
        this.startDate = startDate;
        this.startTime = startTime;
        this.type = type;
    }
}
