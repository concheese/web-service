package net.concheese.server.concert.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

public class Ticketing {
    @Getter @Setter
    private Timestamp start;
    @Getter @Setter
    private Timestamp end;
    @Getter @Setter
    private TicketingType status;

    public Ticketing(Timestamp start, Timestamp end, TicketingType status) {
        this.start = start;
        this.end = end;
        this.status = status;
    }

}
