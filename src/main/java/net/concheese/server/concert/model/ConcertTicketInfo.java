package net.concheese.server.concert.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

/**
 * {@code ConcertTicketInfo} 클래스는 콘서트 티켓 정보를 나타내는 모델입니다.
 */
public class ConcertTicketInfo {
    UUID ticketingID;
    LocalDate startDate;
    LocalDate endDate;
    LocalTime startTime;
    TicketingType type;

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

    public TicketingType getType() {
        return type;
    }

    public void setType(TicketingType type) {
        this.type = type;
    }

    public ConcertTicketInfo(UUID ticketingID, LocalDate startDate, LocalDate endDate, LocalTime startTime, TicketingType type) {
    }
    // TODO: 구현 필요
}
