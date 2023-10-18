package net.concheese.server.concert.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

/**
 * {@code ConcertTicketInfo} 클래스는 콘서트 티켓 정보를 나타내는 모델입니다.
 */
public class ConcertTicketInfo {
    private UUID ticketingID;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd [HH:mm:ss][.SSS][.SS][.S]")
    private LocalDate start;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd [HH:mm:ss][.SSS][.SS][.S]")
    private LocalDate end;
    private TicketingType status;

    public ConcertTicketInfo() {
    }

    public ConcertTicketInfo(LocalDate start, LocalDate end, TicketingType status) {
        this.start = start;
        this.end = end;
        this.status = status;
    }

    public ConcertTicketInfo(UUID ticketingID, LocalDate start, LocalDate end, TicketingType status) {
        this.ticketingID = ticketingID;
        this.start = start;
        this.end = end;
        this.status = status;
    }


    public UUID getTicketingID() {
        return ticketingID;
    }
    public void setTicketingID(UUID ticketingID) {
        this.ticketingID = ticketingID;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public TicketingType getStatus() {
        return status;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public void setStatus(TicketingType status) {
        this.status = status;
    }
}
